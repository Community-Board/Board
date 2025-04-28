package com.example.jin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.jin.dto.request.BoardReqDTO;
import com.example.jin.vo.BoardVO;

public interface BoardService {
	
	List<BoardVO> getAllList(@Param("page") int page, @Param("pageSize") int pageSize);					// 전체 게시글 조회
	
	BoardVO getByNo(Long boardNo);				// 게시글 한건 조회
	
	void insertBoard(BoardReqDTO boardReqDTO);	// 등록
	
	void updateBoard(BoardReqDTO boardReqDTO);	// 수정
	
	void deleteBoard(Long boardNo);				// 삭제
	
	void boardCount(Long boardNo);				// 조회수
		
	void boardLike(Long boardNo);				// 좋아요
}
