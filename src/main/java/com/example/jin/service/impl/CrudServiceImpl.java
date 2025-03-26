package com.example.jin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jin.mapper.CrudMapper;
import com.example.jin.service.CrudService;
import com.example.jin.vo.CrudVO;

@Service
public class CrudServiceImpl implements CrudService{
	private final CrudMapper crudMapper;
	
	// 생성자
    public CrudServiceImpl(CrudMapper crudMapper) {
        this.crudMapper = crudMapper;
    }

	@Override
	public List<CrudVO> getAllList() {
		return crudMapper.getAllList();
	}

	@Override
	public CrudVO getById(String id) {
		return crudMapper.getById(id);
	}

	@Override
	public void insertBoard(CrudVO crudVO) {
		crudMapper.insertBoard(crudVO);
	}

	@Override
	public void updateBoard(CrudVO crudVO) {
		crudMapper.updateBoard(crudVO);
	}

	@Override
	public void deleteBoard(String id) {
		crudMapper.deleteBoard(id);
	}
	
}
