package com.example.board.mapper;

import com.example.board.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserEntity findByUserId(@Param("userId") String userId);
    UserEntity findByUserNick(@Param("userNick") String userNick);
    void insertUser(UserEntity user);
}
