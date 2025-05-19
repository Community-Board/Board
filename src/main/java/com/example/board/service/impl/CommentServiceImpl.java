package com.example.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.dto.request.CommentReqDTO;
import com.example.board.mapper.CommentMapper;
import com.example.board.service.CommentService;
import com.example.board.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentMapper commentMapper;
	
	public CommentServiceImpl(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
	
	@Override
	public List<CommentVO> getAllList(int page, int pageSize) {
		return commentMapper.getAllList(page, pageSize);
	}

	@Override
	public CommentVO getByCommentNo(Long commentNo) {
		return commentMapper.getByCommentNo(commentNo);
	}

	@Override
	public CommentVO getBycommentPlusNo(Long commentPlusNo) {
		return commentMapper.getBycommentPlusNo(commentPlusNo);
	}

	@Override
	public void insertComment(CommentReqDTO commentReqDto) {
		
		CommentVO commentVO = new CommentVO();
		commentVO.setCommentContent(commentReqDto.getCommentContent());
		commentVO.setCommentPlusNo(commentReqDto.getCommentPlusNo());
		commentVO.setUserId(commentReqDto.getUserId());
		
		commentMapper.insertComment(commentVO);
	}

	@Override
	public void updateComment(CommentReqDTO commentReqDto) {
		
		CommentVO commentVO = new CommentVO();
		commentVO.setCommentContent(commentReqDto.getCommentContent());
		commentVO.setCommentNo(commentReqDto.getCommentNo());
		
		commentMapper.updateComment(commentVO);
	}

	@Override
	public void deleteComment(Long commentNo) {
		commentMapper.deleteComment(commentNo);
	}

}
