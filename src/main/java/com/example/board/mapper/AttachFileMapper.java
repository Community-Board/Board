package com.example.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.vo.AttachFileVO;

@Mapper
public interface AttachFileMapper {
	
	int uploadFile(AttachFileVO attachFileVO);
}
