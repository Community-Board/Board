package com.example.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.board.dto.request.CommentReqDTO;
import com.example.board.vo.CommentVO;

public interface CommentService {
	List<CommentVO> getAllList(@Param("page") int page, @Param("pageSize") int pageSize);	// 댓글 전체 조회									
	
	CommentVO getByCommentNo(Long commentNo);	// 댓글조회
	
	CommentVO getBycommentPlusNo(Long commentPlusNo);	// 대댓글 조회 	
	
	void insertComment(CommentReqDTO commentReqDTO);	// 등록
	
	void updateComment(CommentReqDTO commentReqDTO);	// 수정
	
	void deleteComment(Long commentNo);	// 삭제
}
