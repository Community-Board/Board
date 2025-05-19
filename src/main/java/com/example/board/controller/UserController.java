package com.example.board.controller;

import com.example.board.dto.request.UserLoginRequestDto;
import com.example.board.dto.request.UserRegisterRequestDto;
import com.example.board.service.UserService;
import com.example.board.exception.TokenException;
import com.example.board.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequestDto requestDto) {
        String token = userService.login(requestDto);
        Map<String, String> response = new HashMap<>(); // 토큰을 json 형식으로 전달하기위한 map<>형식 사용
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequestDto dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        userService.register(dto);
        return ResponseEntity.ok("회원가입 성공");
    }
        
    @GetMapping("/mypage")
    public ResponseEntity<String> getMyInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new TokenException("Authorization 헤더가 잘못됨");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.validateAndGetUserNick(token);
        return ResponseEntity.ok("안녕하세요, " + username + "님");
    }
       
}
