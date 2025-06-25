package com.example.board.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.board.dto.request.AttachFileReqDTO;
import com.example.board.mapper.AttachFileMapper;
import com.example.board.service.AttachFileService;
import com.example.board.vo.AttachFileVO;

@Service
public class AttachFileServiceImpl implements AttachFileService{
  
  @Value("${file.uploadpath}")
  private String uploadPath; 
  private final AttachFileMapper attachFileMapper;
  
    public AttachFileServiceImpl(AttachFileMapper attachFileMapper) {
      this.attachFileMapper = attachFileMapper;
    }

    private List<Long> uploadFile(List<MultipartFile> files, Long boardNo) throws IOException {
      
      List<Long> fileNoList = new ArrayList<>();
      
      if (files == null || files.isEmpty()) {
        throw new IllegalArgumentException("파일이 비어있습니다.");
      }
      
      for (MultipartFile file : files) {
        if (file.isEmpty()) continue;
        
      // 파일 원본명 추출
      String fileName = file.getOriginalFilename();
      
      // 파일 이름으로 쓸 uuid 생성 (중복되지 않기위해 임의로 지정되는 중복 없는 값)
      String uuid = UUID.randomUUID().toString();
      
      // 확장자 추출(ex - jpg, png ..)
      String extension = fileName.substring(fileName.lastIndexOf("."));
      
      // uuid + 확장자 (ex - rmsidgksmsrjdla.jpg)
      String saveName = uuid + extension;
      
      // 파일 경로
      String fileSrc = uploadPath + saveName; 

      // 실제로 로컬에 uuid를 파일명으로 저장
      file.transferTo(new File(fileSrc));
      
      AttachFileVO attachFileVO = AttachFileVO.builder()
          .fileName(fileName)
          .boardNo(boardNo)
          .fileSrc(fileSrc)
          .build();

      attachFileMapper.uploadFile(attachFileVO);
      fileNoList.add(attachFileVO.getFileNo());
      }
      
      return fileNoList;
    }
    
    @Override
    public List<Long> uploadFile(AttachFileReqDTO attachFileReqDTO) throws IOException {
      
      return uploadFile(attachFileReqDTO.getFiles(), attachFileReqDTO.getBoardNo());
    }

    @Override
    public ResponseEntity<Resource> downLoadFile(Long fileNo) throws IOException {
      
      // fileNo를 통해 파일 정보를 vo에 담기
      AttachFileVO fileInfo = attachFileMapper.selectFile(fileNo);
      
      if(fileInfo == null) {
        throw new IllegalArgumentException("파일이 비어있습니다.");
      }
      
      System.out.println("fileInfo : " + fileInfo);
      
      // DB에 저장된 파일 정보 얻기
      String fileSrc = fileInfo.getFileSrc();
      String fileName = fileInfo.getFileName();
      
      // 실제 파일을 읽기 위한 InputStreamResource 생성
      Path path = Paths.get(fileSrc);
      Resource resource = new InputStreamResource(Files.newInputStream(path));
      
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + fileName)
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .body(resource);
    }

    @Override
    public List<Long> updateFile(AttachFileReqDTO attachFileReqDTO, Long fileNo) throws IOException {
      
      // 기본 정보 삭제 (ex - dog1.jpg --> dog2.jpg)
      AttachFileVO deleteVO = AttachFileVO.builder()
          .fileNo(fileNo)
          .build();
      
      int delResult = attachFileMapper.updateFile(deleteVO);
      
      if(delResult == 0) {
        throw new IllegalArgumentException("파일 삭제가 안되었다.");
      }
      
      // 새 파일 업로드 
      List<MultipartFile> files = attachFileReqDTO.getFiles();
      
      if (files == null || files.isEmpty()) {
        throw new IllegalArgumentException("파일이 비어있습니다.");
      }
      
      return uploadFile(files, attachFileReqDTO.getBoardNo());
    }

    @Override
    public List<Long> deleteFile(AttachFileReqDTO attachFileReqDTO, Long fileNo) throws IOException {
      
      AttachFileVO deleteVO = AttachFileVO.builder()
          .fileNo(fileNo)
          .build();
      
      int delResult = attachFileMapper.deleteFile(deleteVO);
      
      if(delResult == 0) {
        throw new IllegalArgumentException("파일 삭제가 안되었다.");
      }
      
      return List.of(fileNo); 
    }

}
