package com.example.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	List<BoardVO> getAllList(@Param("pageSize") int pageSize, @Param("offset") int offset);	// 전체 게시글 조회
	
	BoardVO getByNo(Long boardNo);			// 게시글 한건 조회
	
	void insertBoard(BoardVO boardVO);		// 등록
	
	int updateBoard(BoardVO boardVO);		// 수정
	
	void deleteBoard(Long boardNo);			// 삭제
	
	void boardCount(Long boardNo);			// 조회수
		
	void boardLike(Long boardNo);			// 좋아요
}
