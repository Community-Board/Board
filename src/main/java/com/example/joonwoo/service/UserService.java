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
        System.out.println("로그인 서비스 호출됨");

        UserEntity user = userMapper.findByUserId(requestDto.getUserId());

        if (user == null) {
            throw new RuntimeException("존재하지 않는 사용자");
        } // 지워버렸는데 NullPointerException 발생...(예외처리 생각해보기) 없앨거라면 pw확인하는 로직 전에 null체크하는 로직 만들어야하나?

        if (!user.getUserPw().equals(requestDto.getUserPw())) {
            throw new RuntimeException("비밀번호가 일치하지 않음");
        }

        String token = jwtUtil.generateToken(user);
        System.out.println("발급된 토큰: " + token);
        return token;
    }
}
