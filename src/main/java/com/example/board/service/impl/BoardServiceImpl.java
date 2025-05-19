package com.example.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.dto.request.BoardReqDTO;
import com.example.board.mapper.BoardMapper;
import com.example.board.service.BoardService;
import com.example.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardVO> getAllList(int page, int pageSize) {
		return boardMapper.getAllList(page, pageSize);
	}

	@Override
	public BoardVO getByNo(Long boardNo) {
		return boardMapper.getByNo(boardNo);
	}

	@Override
	public void insertBoard(BoardReqDTO boardReqDTO) {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardTitle(boardReqDTO.getBoardTitle());
		boardVO.setBoardContent(boardReqDTO.getBoardContent());
		boardVO.setBoardType(boardReqDTO.getBoardType());
		boardVO.setUserNo(boardReqDTO.getUserNo());
		
		boardMapper.insertBoard(boardVO);
	}

	@Override
	public void updateBoard(BoardReqDTO boardReqDTO) {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardTitle(boardReqDTO.getBoardTitle());
		boardVO.setBoardContent(boardReqDTO.getBoardContent());
		boardVO.setBoardType(boardReqDTO.getBoardType());
		boardVO.setUserNo(boardReqDTO.getUserNo());
		
		boardMapper.updateBoard(boardVO);
	}

	@Override
	public void deleteBoard(Long boardNo) {
		boardMapper.deleteBoard(boardNo);
	}

	@Override
	public void boardCount(Long boardNo) {
		boardMapper.boardCount(boardNo);
	}

	@Override
	public void boardLike(Long boardNo) {
		boardMapper.boardLike(boardNo);
	}

}
