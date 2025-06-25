package com.example.board.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachFileVO {
	private Long fileNo;				// 파일번호
	private String fileName;			// 파일명
	private String fileSrc;				// 파일경로
	private LocalDateTime createdAt;		
	private LocalDateTime updatedAt;	
	private boolean isDeleted;
	private Long boardNo;
}
