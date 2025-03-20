package com.example.yun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


// DB -> Datasoruce -> mybatis -> (제목, 내용, 날짜) -> CRUD

/**
 * 1. 게시글 목록
 * 2. 게시글 단건 조회
 * 3. 게시글 작성
 * 4. 게시글 수정
 * 5. 게시글 삭제
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/test")
    public String test2() {
        return "test";
    }
}
