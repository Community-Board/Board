package com.example.board.controller;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<Long> uploadFile(@ModelAttribute AttachFileReqDTO attachFileReqDTO) throws IOException{

    Long fileNo = attachFileService.uploadFile(attachFileReqDTO);
    
    return ResponseEntity.ok(fileNo);
  }
}
