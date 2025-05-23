package com.example.board.dto.response;


import lombok.Data;

@Data
public class BoardResDTO {
	
	private String boardTitle;				// 게시글 제목
	private String boardContent;			// 게시글 내용
	private int boardType;					// 게시글 종류 	ex) 1: 일반 2: 공지
	
	private int boardCount;					// 게시글 조회수
	private int boardLike;					// 게시글 좋아요
	
}
