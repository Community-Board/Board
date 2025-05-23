package com.example.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.dto.request.CommentReqDTO;
import com.example.board.entity.UserEntity;
import com.example.board.mapper.CommentMapper;
import com.example.board.mapper.UserMapper;
import com.example.board.service.CommentService;
import com.example.board.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentMapper commentMapper;
	private final UserMapper userMapper;
	
	
	public CommentServiceImpl(CommentMapper commentMapper, UserMapper userMapper) {
		this.commentMapper = commentMapper;
		this.userMapper = userMapper;
	}
	
	@Override
	public List<CommentVO> getAllList(int pageSize, int offset) {
		return commentMapper.getAllList(pageSize, offset);
	}

	@Override
	public CommentVO getByCommentNo(Long commentNo) {
		return commentMapper.getByCommentNo(commentNo);
	}

	@Override
	public List<CommentVO> getByCommentPlusNo(int pageSize, int offset, Long commentPlusNo) {
		return commentMapper.getByCommentPlusNo(pageSize, offset, commentPlusNo);
	}
	
	@Override
	public List<CommentVO> getByUserNick(int pageSize, int offset, String userNick) {
		return commentMapper.getByUserNick(pageSize, offset, userNick);
	}

	@Override
	public void insertComment(CommentReqDTO commentReqDto, String userNick) {
		
		// userNick -> userId 조회
		UserEntity user = userMapper.findByUserNick(userNick);
		if(user == null) {
			throw new RuntimeException("해당 닉네임이 없습니다.");
		}
		
		String userId = user.getUserId();
		
		CommentVO commentVO = new CommentVO();
		commentVO.setCommentContent(commentReqDto.getCommentContent());
		commentVO.setCommentPlusNo(commentReqDto.getCommentPlusNo());
		commentVO.setUserId(userId);
		
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
