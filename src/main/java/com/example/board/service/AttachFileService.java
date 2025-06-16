package com.example.board.service;

import java.io.IOException;
import com.example.board.dto.request.AttachFileReqDTO;

public interface AttachFileService {
	
    Long uploadFile(AttachFileReqDTO attachFileReqDTO) throws IOException;
}
