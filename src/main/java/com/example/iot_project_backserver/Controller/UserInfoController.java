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
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> userInfo(@RequestParam("Token") String Token) {
        Map<String, String> response = new HashMap<>();

        System.out.println(Token);
        System.out.println(Token);
        System.out.println(Token);
        System.out.println(Token);
        System.out.println(Token);
        System.out.println(Token);

        // DB에서 사용자를 검색하여 인증
        Optional<app_user> userOptional = userService.getRefresh_token(Token);

        if (userOptional.isPresent()) {
            app_user user = userOptional.get();
            // 필요한 정보 추가
            response.put("status", "success");
            response.put("name", user.getName());
            response.put("email", user.getUserid());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "TokenFail"); // 토큰이 유효하지 않은 경우
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}