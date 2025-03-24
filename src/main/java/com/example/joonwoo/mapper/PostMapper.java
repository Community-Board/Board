package com.example.joonwoo.mapper;

import com.example.joonwoo.entity.PostEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    List<PostEntity> getAllPosts(); // 게시글 목록
    PostEntity getPostById(@Param("id") int id); // 게시글 단건 조회
    void insertPost(PostEntity post); // 게시글 작성
    void updatePost(PostEntity post); // 게시글 수정
    void deletePost(@Param("id")int id); // 게시글 삭제 soft delete
}
