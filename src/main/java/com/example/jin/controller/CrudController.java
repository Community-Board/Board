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

import com.example.jin.service.CrudService;
import com.example.jin.vo.CrudVO;

@RestController
@RequestMapping("/crud")
public class CrudController {
	
	private final CrudService crudService;
	
	// 생성자
	public CrudController(CrudService crudService) {
		this.crudService = crudService;
	}
	
	@GetMapping
	public List<CrudVO> getAllList() {
		return crudService.getAllList();
	}
	
	@GetMapping("/{id}")
	public CrudVO getById(@PathVariable String id) {
		return crudService.getById(id);
	}
	
	@PostMapping
	public void insertBoard(@RequestBody CrudVO crudVO) {
		crudService.insertBoard(crudVO);
	}
	
	@PutMapping("/{id}")
	public void updateBoard(@PathVariable String id, @RequestBody CrudVO crudVO) {
		crudVO.setId(id);
		crudService.updateBoard(crudVO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable String id) {
		crudService.deleteBoard(id);
	}
}
