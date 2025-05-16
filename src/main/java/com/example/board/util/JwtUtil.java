package com.example.board.util;

import com.example.board.entity.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "secretkeysecretkeysecretkeysecretkeysecretkey"; // 32바이트 이상
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
    public String generateToken(UserEntity user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        System.out.println("토큰 생성 시 유저 역할: " + user.getUserRole());
        
        return Jwts.builder()
                .claim("userNo", user.getUserNo())
                .claim("userRole", user.getUserRole())
                .claim("userNick", user.getUserNick())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setIssuer("Board-server")
                .setAudience("web-client")
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 검증 후 Subject 추출
    public String validateAndGetUserNick(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String userNick = claims.get("userNick", String.class);
        return userNick;
    }
    
    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }
    }
    
}
