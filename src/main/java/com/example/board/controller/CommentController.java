package com.example.board.controller;

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

import com.example.board.dto.request.CommentReqDTO;
import com.example.board.service.CommentService;
import com.example.board.util.JwtUtil;
import com.example.board.util.PageUtil;
import com.example.board.vo.CommentVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private final CommentService commentService;
	private final JwtUtil jwtUtil;
	
	public CommentController(CommentService commentService, JwtUtil jwtUtil) {
		this.commentService = commentService;
		this.jwtUtil = jwtUtil;
	}
	
	
	@GetMapping
	public List<CommentVO> getAllList(@RequestParam(defaultValue = "1")int page, 
									  @RequestParam(defaultValue = "50") int pageSize){
		
		int offset = PageUtil.calculateOffset(page, pageSize);
		
		return commentService.getAllList(pageSize, offset);
	}
	
	@GetMapping("/no/{commentNo}")
	public CommentVO getByCommentNo(@PathVariable Long commentNo) {
		
		return commentService.getByCommentNo(commentNo);
	}
	
	@GetMapping("/reply/{commentPlusNo}")
	public List<CommentVO> getByCommentPlusNo(@RequestParam(defaultValue = "1")int page, 
										@RequestParam(defaultValue = "50") int pageSize,
										@PathVariable Long commentPlusNo) {
		
		int offset = PageUtil.calculateOffset(page, pageSize);
		
		return commentService.getByCommentPlusNo(pageSize, offset, commentPlusNo);
	}
	
	@GetMapping("/nick/{userNick}")
	public List<CommentVO> getByUserNick(@RequestParam(defaultValue = "1")int page, 
										@RequestParam(defaultValue = "50") int pageSize,
										@PathVariable String userNick) {
		
		int offset = PageUtil.calculateOffset(page, pageSize);
		
		return commentService.getByUserNick(pageSize, offset, userNick);
	}
	
	@PostMapping
	public ResponseEntity<?> insertComment(@Valid @RequestBody CommentReqDTO commentReqDto,
											@RequestHeader("Authorization") String authHeader) {
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 없음");
		}
		
		String token = authHeader.substring(7);
		String userNick = jwtUtil.validateAndGetUserNick(token);
		
		commentService.insertComment(commentReqDto, userNick);
		
		return ResponseEntity.ok("댓글 작성 성공");
	}
	
	@PutMapping("/{commentNo}")
	public void updateComment(@Valid @RequestBody CommentReqDTO commentReqDto) {
		commentService.updateComment(commentReqDto);
	}
	
	@DeleteMapping("/{commentNo}")
	public void deleteComment(@PathVariable Long commentNo) {
		commentService.deleteComment(commentNo);
	}
}
