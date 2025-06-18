package com.example.board.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.board.dto.request.AttachFileReqDTO;
import com.example.board.service.AttachFileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor 
public class AttachFileController {
  
  private final AttachFileService attachFileService;
  
  @PostMapping
  public ResponseEntity<List<Long>> uploadFile(@ModelAttribute AttachFileReqDTO attachFileReqDTO) throws IOException{

    List<Long> fileNo = attachFileService.uploadFile(attachFileReqDTO);
    
    return ResponseEntity.ok(fileNo);
  }
  
  @GetMapping("/{fileNo}")
  public ResponseEntity<Resource> downLoadFile(@PathVariable Long fileNo) throws IOException{
    
    return attachFileService.downLoadFile(fileNo);
  }
  
  @PutMapping("/{fileNo}")
  public ResponseEntity<List<Long>> updateFile(@ModelAttribute AttachFileReqDTO attachFileReqDTO, @PathVariable Long fileNo) throws IOException{

    List<Long> updateFile = attachFileService.updateFile(attachFileReqDTO, fileNo);
    
    return ResponseEntity.ok(updateFile);
  }
  
  @DeleteMapping("/{fileNo}")
  public ResponseEntity<List<Long>> deleteFile(@ModelAttribute AttachFileReqDTO attachFileReqDTO, @PathVariable Long fileNo) throws IOException{
    
    List<Long> deleteFile =  attachFileService.deleteFile(attachFileReqDTO, fileNo);
    
    return ResponseEntity.ok(deleteFile);
  }
}