package com.example.joonwoo.service;

import com.example.joonwoo.dto.UserLoginRequestDto;
import com.example.joonwoo.entity.UserEntity;
import com.example.joonwoo.mapper.UserMapper;
import com.example.joonwoo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public String login(UserLoginRequestDto requestDto) {
        UserEntity user = userMapper.findByUserId(requestDto.getUserId());

        if (user == null) {
            throw new RuntimeException("존재하지 않는 사용자");
        }

        if (!user.getUserPw().equals(requestDto.getUserPw())) {
            throw new RuntimeException("비밀번호가 일치하지 않음");
        }

        return jwtUtil.generateToken(user);
    }
}
