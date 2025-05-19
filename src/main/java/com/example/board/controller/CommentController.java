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

import com.example.board.dto.request.CommentReqDTO;
import com.example.board.service.CommentService;
import com.example.board.vo.CommentVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private final CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping
	public List<CommentVO> getAllList(@RequestParam(defaultValue = "1")int page, 
									  @RequestParam(defaultValue = "10") int pageSize){
		return commentService.getAllList(page, pageSize);
	}
	
	@GetMapping("/{commentNo}")
	public CommentVO getByCommentNo(@PathVariable Long commentNo) {
		return commentService.getByCommentNo(commentNo);
	}
	
	@GetMapping("/reply/{commentPlusNo}")
	public CommentVO getByCommentPlusNo(@PathVariable Long commentPlusNo) {
		return commentService.getBycommentPlusNo(commentPlusNo);
	}
	
	@PostMapping
	public void insertComment(@Valid @RequestBody CommentReqDTO commentReqDto) {
		commentService.insertComment(commentReqDto);
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
