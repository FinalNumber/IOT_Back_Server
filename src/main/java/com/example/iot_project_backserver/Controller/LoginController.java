package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.entity.app_user;
import com.example.iot_project_backserver.service.UserService;
import com.example.iot_project_backserver.config.jwt.TokenProvider; // TokenProvider 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider; // TokenProvider 주입

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email,
                                                     @RequestParam("password") String password) {
        Map<String, String> response = new HashMap<>();

        // DB에서 사용자를 검색하여 인증
        Optional<app_user> userOptional = userService.getUserById(email);

        if (userOptional.isPresent()) {
            app_user user = userOptional.get();

            // 비밀번호가 일치하는지 확인
            if (passwordEncoder.matches(password, user.getPassword())) {
                String accessToken = tokenProvider.generateAccessToken(user); // 억세스 토큰 생성
                String refreshToken = tokenProvider.generateRefreshToken(user); // 리프레시 토큰 생성

                System.out.println(accessToken);
                System.out.println(refreshToken);

                response.put("status", "success");
                response.put("accessToken", accessToken); // 억세스 토큰 추가
                response.put("refreshToken", refreshToken); // 리프레시 토큰 추가
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "PasswordFail"); // 비밀번호 불일치
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.put("status", "IdFail"); // 존재하지 않는 ID
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
