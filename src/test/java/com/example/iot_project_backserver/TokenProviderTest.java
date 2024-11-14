package com.example.iot_project_backserver;

import com.example.iot_project_backserver.Entity.User.app_user;
import com.example.iot_project_backserver.Security.Config.Jwt.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    private app_user testUser;

    @BeforeEach
    public void setUp() {
        testUser = new app_user();
        testUser.setUserid("testuser123"); // 테스트용 사용자 ID 설정
    }

    @Test
    public void testGenerateAndValidateAccessToken() {
        // 억세스 토큰 생성
        String accessToken = tokenProvider.generateAccessToken(testUser);
        assertNotNull(accessToken, "Access token should not be null");

        // 토큰 유효성 검증
        boolean isAccessTokenValid = tokenProvider.validateToken(accessToken);
        assertTrue(isAccessTokenValid, "Access token should be valid");

        System.out.println("Generated Access Token: " + accessToken);
    }

    @Test
    public void testGenerateAndValidateRefreshToken() {
        // 리프레시 토큰 생성
        String refreshToken = tokenProvider.generateRefreshToken(testUser);
        assertNotNull(refreshToken, "Refresh token should not be null");

        // 토큰 유효성 검증
        boolean isRefreshTokenValid = tokenProvider.validateToken(refreshToken);
        assertTrue(isRefreshTokenValid, "Refresh token should be valid");

        System.out.println("Generated Refresh Token: " + refreshToken);
    }
}
