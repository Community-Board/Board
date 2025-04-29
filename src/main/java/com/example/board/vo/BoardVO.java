package com.example.board.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardVO {
	private Long boardNo;					// 게시글 번호
	private String boardTitle;				// 게시글 제목
	private String boardContent;			// 게시글 내용
	private int boardCount;					// 게시글 조회수
	private int boardLike;					// 게시글 좋아요
	private int boardType;					// 게시글 종류 	ex) 1: 일반 2: 공지
	private Long userNo;					// 유저 번호
	private LocalDateTime createdAt;		// 게시글 작성시간
	private LocalDateTime updatedAt;		// 게시글 수정시간
	private boolean isDeleted;
}
