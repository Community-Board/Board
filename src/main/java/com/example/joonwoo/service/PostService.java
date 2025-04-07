package com.example.joonwoo.service;

import com.example.joonwoo.dto.PostRequestDto;
import com.example.joonwoo.dto.PostResponseDto;
import com.example.joonwoo.entity.PostEntity;
import com.example.joonwoo.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

//    public PostService(PostMapper postMapper) { // 생성자
//        this.postMapper = postMapper;
//    }

    public List<PostResponseDto> getAllPosts() {
        return postMapper.getAllPosts().stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt(),
                        post.getUpdatedAt()))
                .toList();
    }

    public PostResponseDto getPostById(int id) {
        PostEntity post = postMapper.getPostById(id);
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt());
    }

    public void insertPost(PostRequestDto dto) { // 게시글 작성
        PostEntity post = new PostEntity();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postMapper.insertPost(post);
    }
    
    public void updatePost(int id, PostRequestDto dto) { // 게시글 수정
        PostEntity post = new PostEntity();
        post.setId(id);
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postMapper.updatePost(post);
    }

    public void deletePost(int id) { // 게시글 삭제 soft delete
        postMapper.deletePost(id);
    }    
}
