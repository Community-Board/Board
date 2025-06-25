package com.example.board.dto.request;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class AttachFileReqDTO {
    
    private List <MultipartFile> files;
    private Long boardNo;
}
