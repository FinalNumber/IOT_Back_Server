package com.example.iot_project_backserver.Controller.User;

import com.example.iot_project_backserver.entity.User.app_user;
import com.example.iot_project_backserver.service.User.UserService;
import com.example.iot_project_backserver.config.jwt.TokenProvider;
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
    private TokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestParam("email") String email,
                                                     @RequestParam("password") String password) {
        Map<String, Object> response = new HashMap<>();

        // DB에서 사용자를 검색하여 인증
        Optional<app_user> userOptional = userService.getUserById(email);

        if (userOptional.isPresent()) {
            app_user user = userOptional.get();

            // 비밀번호가 일치하는지 확인
            if (passwordEncoder.matches(password, user.getPassword())) {
                String accessToken = tokenProvider.generateAccessToken(user);
                String refreshToken = tokenProvider.generateRefreshToken(user);

                user.setRefresh_token(refreshToken);
                userService.saveUser(user);

                // DT에 응답 데이터 넣기
                Map<String, String> responseData = new HashMap<>();
                responseData.put("accessToken", accessToken);
                responseData.put("refreshToken", refreshToken);
                responseData.put("userid", user.getUserid());
                responseData.put("name", user.getName());
                responseData.put("birth", user.getBirth());
                responseData.put("phone_num", user.getPhone_num());
                responseData.put("division", user.getDivision());
                responseData.put("status", "success");

                response.put("status", "success");
                response.put("data", responseData);

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
