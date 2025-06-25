package com.example.board.service;

import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import com.example.board.dto.request.AttachFileReqDTO;

public interface AttachFileService {
	
    List<Long> uploadFile(AttachFileReqDTO attachFileReqDTO) throws IOException;
    
    ResponseEntity<Resource> downLoadFile(Long fileNo) throws IOException;
    
    List<Long> updateFile(AttachFileReqDTO attachFileReqDTO, Long fileNo) throws IOException;
    
    List<Long> deleteFile(AttachFileReqDTO attachFileReqDTO, Long fileNo) throws IOException;
}
