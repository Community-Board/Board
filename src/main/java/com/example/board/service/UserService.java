package com.example.board.service;

import com.example.board.dto.request.UserLoginRequestDto;
import com.example.board.dto.request.UserRegisterRequestDto;
import com.example.board.mapper.UserMapper;
import com.example.board.entity.UserEntity;
import com.example.board.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public String login(UserLoginRequestDto requestDto) {
        UserEntity user = userMapper.findByUserId(requestDto.getUserId());

        if (user == null) {
            throw new RuntimeException("존재하지 않는 사용자");
        }
        if (!passwordEncoder.matches(requestDto.getUserPw(), user.getUserPw())) {
            throw new RuntimeException("비밀번호가 일치하지 않음");
        }

        return jwtUtil.generateToken(user);
    }
    
    public void register(UserRegisterRequestDto dto) {
        String encodedPw = passwordEncoder.encode(dto.getUserPw());
        dto.setUserPw(encodedPw);
        userMapper.insertUser(dto.toEntity());
    }
}
