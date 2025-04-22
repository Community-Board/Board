package com.example.joonwoo.service;

import com.example.joonwoo.dto.UserLoginRequestDto;
import com.example.joonwoo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;

    public String login(UserLoginRequestDto requestDto) {
        System.out.println("로그인 서비스 호출됨");

        if ("testuser".equals(requestDto.getUsername()) && "0000".equals(requestDto.getPassword())) {

            String token = jwtUtil.generateToken(requestDto.getUsername());
            System.out.println("발급된 토큰: " + token);
            return token;
        } else {
            throw new RuntimeException("아이디 또는 비밀번호가 잘못됨");
        }
    }
}
