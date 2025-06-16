package com.example.board.dto.request;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class AttachFileReqDTO {
    
    private MultipartFile file;
    private Long boardNo;
}
