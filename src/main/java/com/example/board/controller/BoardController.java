package com.example.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.request.BoardReqDTO;
import com.example.board.entity.UserEntity;
import com.example.board.mapper.UserMapper;
import com.example.board.service.BoardService;
import com.example.board.util.JwtUtil;
import com.example.board.vo.BoardVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	private final JwtUtil jwtUtil;
	private final UserMapper userMapper;
	
	public BoardController(BoardService boardService, JwtUtil jwtUtil, UserMapper userMapper) {
		this.boardService = boardService;
		this.jwtUtil = jwtUtil;
		this.userMapper = userMapper;
	}
	
	@GetMapping
	public List<BoardVO> getAllList(@RequestParam(defaultValue = "1")int page, 
									@RequestParam(defaultValue = "10") int pageSize) {
		return boardService.getAllList(page, pageSize);
	}
	
	@GetMapping("/{boardNo}")
	public BoardVO getByNo(@PathVariable Long boardNo) {
		return boardService.getByNo(boardNo);
	}
	
	@PostMapping
	public ResponseEntity<String> insertBoard(@Valid @RequestBody BoardReqDTO boardReqDTO,
											@RequestHeader("Authorization") String authHeader) throws IOException {
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 없음");
		}
		
		String token = authHeader.substring(7);
		String userNick = jwtUtil.validateAndGetUserNick(token);
		
		// 로그인 정보에 따라 게시글 분류 (1: 일반 게시글 , 2: 공지 게시글
		/*
		UserEntity user = userMapper.findByUserNick(userNick);
		String role = user.getUserRole();		// user , admin
		
		if(! "admin".equals(role)) {
			boardReqDTO.setBoardType(1);
		} else {
			if(boardReqDTO.getBoardType() != 1 && boardReqDTO.getBoardType() != 2) {
				 boardReqDTO.setBoardType(1);	
			}
		}
		*/
		
		Long boardNo = boardService.insertBoard(boardReqDTO, userNick);
		
		return ResponseEntity.ok("게시글 작성 성공, 게시글 번호 : " + boardNo);
	}
	
	@PutMapping("/{boardNo}")
	public ResponseEntity<String> updateBoard(@PathVariable Long boardNo, 
										@Valid @RequestBody BoardReqDTO boardReqDTO,
										@RequestHeader("Authorization") String authHeader) {
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 없음");
		}
		
		String token = authHeader.substring(7);
		String userNick = jwtUtil.validateAndGetUserNick(token);
		
		UserEntity user = userMapper.findByUserNick(userNick);
		Long userNo = user.getUserNo();
		
	    boardReqDTO.setBoardNo(boardNo);
	    boardReqDTO.setUserNo(userNo);
		
		boolean result = boardService.updateBoard(boardReqDTO, userNo);
		
	    if (result) {
	        return ResponseEntity.ok("게시글 수정 성공");
	    } else {
	        return ResponseEntity.status(403).body("게시글 수정 실패");
	    }
		
	}
	
	@DeleteMapping("/{boardNo}")
	public void deleteBoard(@PathVariable Long boardNo) {
		boardService.deleteBoard(boardNo);
	}
	
	@PutMapping("/{boardNo}/count")
	public void boardCount(@PathVariable Long boardNo) {
		boardService.boardCount(boardNo);
	}
	
	@PutMapping("/{boardNo}/like")
	public void boardLike(@PathVariable Long boardNo){
		boardService.boardLike(boardNo);
	}
}
