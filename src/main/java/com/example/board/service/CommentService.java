package com.example.board.service;

import java.util.List;


import com.example.board.dto.request.CommentReqDTO;
import com.example.board.vo.CommentVO;

public interface CommentService {
	List<CommentVO> getAllList(int pageSize, int offset);	// 댓글 전체 조회									
	
	CommentVO getByCommentNo(Long commentNo);	// 댓글조회
	
	List<CommentVO> getByCommentPlusNo(int pageSize, int offset, Long commentPlusNo);	// 대댓글 조회
	
	List<CommentVO> getByUserNick(int pageSize, int offset, String userNick);		// 닉네임 조회
	
	void insertComment(CommentReqDTO commentReqDTO, String userNick);	// 등록
	
	void updateComment(CommentReqDTO commentReqDTO);	// 수정
	
	void deleteComment(Long commentNo);	// 삭제
}
