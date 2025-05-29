package com.example.board.interceptor;

import com.example.board.annotation.RoleCheck;
import com.example.board.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RoleInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RoleCheck roleCheck = handlerMethod.getMethodAnnotation(RoleCheck.class);

        if (roleCheck == null) { // RoleCheck 어노테이션이 없다면 통과
            return true;
        }

        String token = request.getHeader("Authorization");
        
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("토큰이 없습니다.");
            return false;
        }

        token = token.substring(7);
        
        Claims claims = jwtUtil.parseToken(token);
        
        try {
            claims = jwtUtil.parseToken(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("유효하지 않은 토큰입니다.");
            return false;
        }

        String userRole = claims.get("userRole", String.class);
        
        String requiredRole = roleCheck.value();
        
        if (!userRole.equals(requiredRole)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("접근 권한이 없습니다.");
            return false;
        }

        return true;
    }
}
