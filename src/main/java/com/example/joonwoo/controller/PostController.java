package com.example.joonwoo.controller;

import com.example.joonwoo.dto.PostRequestDto;
import com.example.joonwoo.dto.PostResponseDto;
import com.example.joonwoo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "게시판 API", description = "게시판 CRUD 기능")
public class PostController {
	private final PostService postService;

//    public PostController(PostService postService) { // 생성자
//        this.postService = postService;
//    }

    @GetMapping
    @Operation(summary = "게시글 목록 조회", description = "모든 게시글을 조회합니다.")
    public List<PostResponseDto> getAllPosts() { // GET 방식을 통한 게시글 목록
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 단건 조회", description = "게시글을 조회합니다.")
    public PostResponseDto getPostById(@PathVariable int id) { // GET 방식을 통한 게시글 단건 조회
        return postService.getPostById(id);
    }

    @PostMapping
    @Operation(summary = "게시글 작성", description = "새로운 게시글을 작성합니다.")
    public String insertPost(@RequestBody PostRequestDto requestDto) { // POST 방식을 통해 게시글 작성(JSON 방식으로 게시글 전달)
        postService.insertPost(requestDto);
        return "게시글 작성이 완료되었습니다.";
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    public String updatePost(@PathVariable int id, @RequestBody PostRequestDto requestDto) { // PUT 방식을 통해 게시글 수정
        postService.updatePost(id, requestDto); // 수정
        return "게시글 수정이 완료되었습니다.";
    }
    

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    public String deletePost(@PathVariable int id) { // 게시글 삭제
        postService.deletePost(id);
        return "게시글이 삭제되었습니다.";
    }
}
