package com.example.joonwoo.controller;

import com.example.joonwoo.dto.UserLoginRequestDto;
import com.example.joonwoo.service.UserService;
import com.example.joonwoo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequestDto requestDto) {

        String token = userService.login(requestDto);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    // 인증 테스트 API
    @GetMapping("/me")
    public ResponseEntity<String> getMyInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Authorization 헤더가 잘못됨");
        }

        String token = authHeader.substring(7);
        try {
            String username = jwtUtil.validateAndGetUsername(token);
            return ResponseEntity.ok("안녕하세요, " + username + "님");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("토큰이 유효하지 않음");
        }
    }
}
