package com.example.joonwoo.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "secretkeysecretkeysecretkeysecretkeysecretkey";  // 32 바이트 비밀키
    private static final long EXPIRATION_TIME = 86400000; // 24시간

    private SecretKey getSigningKey() {
        byte[] key = SECRET_KEY.getBytes();
        if (key.length < 32) {
            byte[] paddedKey = new byte[32];
            System.arraycopy(key, 0, paddedKey, 0, key.length);
            key = paddedKey;
        }
        return new SecretKeySpec(key, SignatureAlgorithm.HS256.getJcaName());
    }

    // 토큰 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    // 토큰 검증 후 사용자 이름 반환
    public String validateAndGetUsername(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("토큰이 유효하지 않음");
        }
    }
}
