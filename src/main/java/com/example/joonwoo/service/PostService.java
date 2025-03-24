package com.example.joonwoo.service;

import com.example.joonwoo.entity.PostEntity;
import com.example.joonwoo.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) { // 생성자
        this.postMapper = postMapper;
    }

    public List<PostEntity> getAllPosts() { // 게시글 목록
        return postMapper.getAllPosts();
    }

    public PostEntity getPostById(int id) { // 게시글 단건 조회
        return postMapper.getPostById(id);
    }

    public void insertPost(PostEntity post) { // 게시글 작성
        postMapper.insertPost(post);
    }

    public void updatePost(PostEntity post) { // 게시글 수정
        postMapper.updatePost(post);
    }

    public void deletePost(int id) { // 게시글 삭제 soft delete
        postMapper.deletePost(id);
    }
}
