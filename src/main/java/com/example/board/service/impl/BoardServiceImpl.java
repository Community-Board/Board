package com.example.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.dto.request.BoardReqDTO;
import com.example.board.entity.UserEntity;
import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.UserMapper;
import com.example.board.service.BoardService;
import com.example.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardMapper;
	private final UserMapper userMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper, UserMapper userMapper) {
		this.boardMapper = boardMapper;
		this.userMapper = userMapper;
	}

	@Override
	public List<BoardVO> getAllList(int pageSize, int offset) {
		return boardMapper.getAllList(pageSize, offset);
	}

	@Override
	public BoardVO getByNo(Long boardNo) {
		return boardMapper.getByNo(boardNo);
	}

	@Override
	public void insertBoard(BoardReqDTO boardReqDTO, String userNick) {
		
		// userNick -> userId 조회
		UserEntity user = userMapper.findByUserNick(userNick);
		if(user == null) {
			throw new RuntimeException("해당 닉네임이 없습니다.");
		}
		
		Long userNo = user.getUserNo();
		
		BoardVO boardVO = BoardVO.builder()
				.boardTitle(boardReqDTO.getBoardTitle())
				.boardContent(boardReqDTO.getBoardContent())
				.boardType(boardReqDTO.getBoardType())
				.userNo(userNo)
				.build();
	
		boardMapper.insertBoard(boardVO);
	}

	@Override
	public boolean updateBoard(BoardReqDTO boardReqDTO, Long userNo) {
		
		BoardVO boardVO = BoardVO.builder()
				.boardNo(boardReqDTO.getBoardNo())
				.boardTitle(boardReqDTO.getBoardTitle())
				.boardContent(boardReqDTO.getBoardContent())
				.boardType(boardReqDTO.getBoardType())
				.userNo(userNo)
				.build();

		int result = boardMapper.updateBoard(boardVO);
		
		return result > 0;
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
