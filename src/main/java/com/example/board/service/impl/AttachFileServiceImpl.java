package com.example.board.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
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

    @Override
    public Long uploadFile(AttachFileReqDTO attachFileReqDTO) throws IOException {
      
      MultipartFile file = attachFileReqDTO.getFile();
      
      if (file == null || file.isEmpty()) {
        throw new IllegalArgumentException("파일이 비어있습니다.");
      }
      
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
          .boardNo(attachFileReqDTO.getBoardNo())
          .fileSrc(fileSrc)
          .build();

      attachFileMapper.uploadFile(attachFileVO);
      
      return attachFileVO.getFileNo();
    }
}
