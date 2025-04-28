package com.example.jin.service;

import java.util.List;

import com.example.jin.vo.CommentVO;

public interface CommentService {
	List<CommentVO> getAllList();	// 댓글 전체 조회									
	
	CommentVO getByCommentNo(Long commentNo, Long commentPlusNo);	// 댓글조회
	
	CommentVO getBycommentPlusNo(Long commentPlusNo);	// 대댓글 조회 	
	
	void insertComment(CommentVO commentVO);	// 등록
	
	void updateComment(CommentVO commentVO);	// 수정
	
	void deleteComment(CommentVO commentVO);	// 삭제
}
