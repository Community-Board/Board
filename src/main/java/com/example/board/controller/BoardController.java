package com.example.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.request.BoardReqDTO;
import com.example.board.service.BoardService;
import com.example.board.vo.BoardVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
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
	public void insertBoard(@Valid @RequestBody BoardReqDTO boardReqDTO) {
		boardService.insertBoard(boardReqDTO);
	}
	
	@PutMapping("/{boardNo}")
	public void updateBoard(@PathVariable Long boardNo, @Valid @RequestBody BoardReqDTO boardReqDTO) {
		boardService.updateBoard(boardReqDTO);
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
