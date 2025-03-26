package com.example.jin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jin.service.impl.CrudServiceImpl;
import com.example.jin.vo.CrudVO;

@RestController
@RequestMapping("/board")
public class CrudController {
	
	private final CrudServiceImpl crudServiceImpl;
	
	// 생성자
	public CrudController(CrudServiceImpl crudServiceImpl) {
		this.crudServiceImpl = crudServiceImpl;
	}
	
	@GetMapping
	public List<CrudVO> getAllList() {
		return crudServiceImpl.getAllList();
	}
	
	@GetMapping("/{id}")
	public CrudVO getById(@PathVariable String id) {
		return crudServiceImpl.getById(id);
	}
	
	@PostMapping
	public void insertBoard(@RequestBody CrudVO crudVO) {
		crudServiceImpl.insertBoard(crudVO);
	}
	
	@PutMapping("/{id}")
	public void updateBoard(@PathVariable String id, @RequestBody CrudVO crudVO) {
		crudVO.setId(id);
		crudServiceImpl.updateBoard(crudVO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable String id) {
		crudServiceImpl.deleteBoard(id);
	}
}
