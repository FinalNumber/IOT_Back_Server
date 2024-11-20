package com.example.iot_project_backserver.Security.Config.Jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refresh")
public class TokenRefreshController {

    private final TokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Refresh token is missing");
        }

        try {
            String newAccessToken = tokenProvider.validateAndGenerateNewAccessToken(refreshToken);
            System.out.println("갱신 진행중입니다.");
            System.out.println("갱신 진행중입니다.");
            System.out.println("갱신 진행중입니다.");
            System.out.println("갱신 진행중입니다.");
            System.out.println("갱신 진행중입니다.");
            System.out.println("갱신 진행중입니다.");
            return ResponseEntity.ok(Collections.singletonMap("accessToken", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
        }
    }
}
