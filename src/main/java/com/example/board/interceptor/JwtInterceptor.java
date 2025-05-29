package com.example.board.interceptor;

import com.example.board.util.JwtUtil;
import com.example.board.exception.TokenException;
import io.jsonwebtoken.*;
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
                jwtUtil.validateAndGetUserNick(token);
            } catch (ExpiredJwtException e) {
                throw new TokenException("토큰이 만료되었습니다.");
            } catch (UnsupportedJwtException e) {
                throw new TokenException("지원하지 않는 토큰입니다.");
            } catch (MalformedJwtException e) {
                throw new TokenException("토큰 형식이 올바르지 않습니다.");
            } catch (IllegalArgumentException e) {
                throw new TokenException("잘못된 토큰입니다.");
            } catch (JwtException e) {
                throw new TokenException("토큰 오류가 발생했습니다.");
            }
        }

        return true;
    }
}
