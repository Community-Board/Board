package com.example.jin.service;

import java.util.List;

import com.example.jin.vo.CrudVO;

public interface CrudService {
	
	List<CrudVO> getAllList();				// 전체 리스트 조회
	
	CrudVO getById(String id);				// 한건 조회
	
	void insertBoard(CrudVO crudVO);		// 등록
	
	void updateBoard(CrudVO crudVO);		// 수정
	
	void deleteBoard(String id);			// 삭제
}
