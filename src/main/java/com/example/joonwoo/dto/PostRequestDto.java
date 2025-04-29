package com.example.joonwoo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostRequestDto { // 게시글 작성/수정시 클라이언트가 보내는 데이터
    private String title;
    private String content;
}