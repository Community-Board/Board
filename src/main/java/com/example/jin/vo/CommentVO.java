package com.example.jin.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentVO {
	private Long commentNo;				// 댓글 번호
	private String commentContent;		// 댓글 내용
	private Long commentPlusNo;			// 대댓글 번호
	private LocalDateTime createdAt;	// 댓글 작성시간
	private LocalDateTime updatedAt;	// 댓글 수정시간
	private boolean isDeleted;
}	
