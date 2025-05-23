package com.example.board.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentReqDTO {
	
	private Long commentNo;		// 댓글 번호
	
	@NotBlank(message = "댓글 내용이 비어 있을 수 없습니다.")
	@Size(max= 1000)				// 내용 제한은 임의로 둠
	private String commentContent;	// 댓글 내용
	
	private Long commentPlusNo; // 대댓글 번호
	
}
