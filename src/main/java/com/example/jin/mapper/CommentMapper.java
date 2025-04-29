package com.example.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.jin.vo.CommentVO;

@Mapper
public interface CommentMapper {
		
	List<CommentVO> getAllList();	// 댓글 전체 조회									
	
	CommentVO getByCommentNo(Long commentNo, Long commentPlusNo);	// 댓글조회
	
	CommentVO getBycommentPlusNo(Long commentPlusNo);	// 대댓글 조회 	
	
	void insertComment(CommentVO commentVO);	// 등록
	
	void updateComment(CommentVO commentVO);	// 수정
	
	void deleteComment(CommentVO commentVO);	// 삭제
}
