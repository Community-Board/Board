package com.example.joonwoo.interceptor;

import com.example.joonwoo.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String userNick = jwtUtil.validateAndGetUserNick(token);
                System.out.println("인터셉터 - 인증된 사용자: " + userNick);
            } catch (RuntimeException e) {
                System.out.println("인터셉터 - 토큰이 유효하지 않음");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        return true; // 계속 처리하도록 허용
    }
}
