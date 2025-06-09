package com.example.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.board.dto.request.BoardReqDTO;
import com.example.board.vo.BoardVO;

public interface BoardService {
	
	List<BoardVO> getAllList(@Param("pageSize") int pageSize, @Param("offset") int offset);					// 전체 게시글 조회
	
	BoardVO getByNo(Long boardNo);				// 게시글 한건 조회
	
	void insertBoard(BoardReqDTO boardReqDTO, String userNick);	// 등록
	
	boolean updateBoard(BoardReqDTO boardReqDTO, Long userNo);	// 수정
	
	void deleteBoard(Long boardNo);				// 삭제
	
	void boardCount(Long boardNo);				// 조회수
		
	void boardLike(Long boardNo);				// 좋아요
}
