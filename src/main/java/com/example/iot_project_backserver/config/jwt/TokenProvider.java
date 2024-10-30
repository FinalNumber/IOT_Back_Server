package com.example.iot_project_backserver.config.jwt;

import com.example.iot_project_backserver.entity.app_user;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    // SecretKey 생성 메서드
    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 억세스 토큰 생성 메서드
    public String generateAccessToken(app_user user) {
        return generateToken(user, jwtProperties.getAccessTokenExpiration()); // 억세스 토큰 만료 시간 사용
    }

    // JWT 리프레시 토큰 생성 메서드
    public String generateRefreshToken(app_user user) {
        return generateToken(user, jwtProperties.getRefreshTokenExpiration()); // 리프레시 토큰 만료 시간 사용
    }

    // 공통 JWT 토큰 생성 메서드
    private String generateToken(app_user user, long expiredAt) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiredAt);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 타입 : JWT
                .setIssuer(jwtProperties.getIssuer()) // 발행자 설정
                .setIssuedAt(now) // 현재 시간
                .setExpiration(expiryDate) // 만료 시간
                .setSubject(user.getUserid()) // 사용자 ID를 주제로 설정
                .claim("id", user.getUserid()) // 클레임: 유저 ID
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 서명: SecretKey와 함께 HS256 방식으로 암호화
                .compact();
    }

    // JWT 토큰 유효성 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // SecretKey로 복호화
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 복호화 과정에서 에러가 나면 유효하지 않은 토큰
            return false;
        }
    }

    // 토큰 기반으로 인증 정보를 가져오는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", Collections.singleton(authority));
    }

    // 토큰 기반으로 유저 ID를 가져오는 메서드
    public String getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", String.class);
    }

    // Claims 객체를 가져오는 메서드
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // SecretKey로 복호화
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
