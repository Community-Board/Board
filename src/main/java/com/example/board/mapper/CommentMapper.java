package com.example.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.board.vo.CommentVO;

@Mapper
public interface CommentMapper {
		
	List<CommentVO> getAllList(@Param("pageSize") int pageSize, @Param("offset") int offset);	// 댓글 전체 조회									
	
	CommentVO getByCommentNo(@Param("commentNo") Long commentNo);	// 댓글조회
	
	List<CommentVO> getByCommentPlusNo(@Param("pageSize") int pageSize, @Param("offset") int offset, @Param("commentPlusNo") Long commentPlusNo);	// 대댓글 조회
	
	List<CommentVO> getByUserNick(@Param("pageSize") int pageSize, @Param("offset") int offset, @Param("userNick") String userNick);		// 닉네임 조회
	
	void insertComment(CommentVO commentVO);	// 등록
	
	int updateComment(CommentVO commentVO);	// 수정
	
	void deleteComment(Long commentNo);	// 삭제
}
