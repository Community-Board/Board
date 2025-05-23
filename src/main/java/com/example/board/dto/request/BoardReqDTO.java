package com.example.board.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardReqDTO {
	
	private Long boardNo;					// 게시글 번호
	
	@NotBlank(message = "제목은 비어 있을 수 없습니다.")
	@Size(max = 100, message = "제목은 100자 이내로 작성해야 합니다." )
	private String boardTitle;				// 게시글 제목
	
	@NotBlank(message = "내용은 비어 있을 수 없습니다.")
	private String boardContent;			// 게시글 내용
	private int boardType;					// 게시글 종류 	ex) 1: 일반 2: 공지
	
}
