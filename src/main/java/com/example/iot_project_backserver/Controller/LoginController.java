package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.entity.app_user;
import com.example.iot_project_backserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email,
                                                     @RequestParam("password") String password) {
        Map<String, String> response = new HashMap<>();

        String userid = email;
        // DB에서 사용자를 검색하여 인증
        Optional<app_user> userOptional = userService.getUserById(userid);

        if (userOptional.isPresent()) {
            app_user user = userOptional.get();

            // 비밀번호가 일치하는지 확인
            if (user.getPassword().equals(password)) {
                response.put("status", "success"); //로그인 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
                //return ResponseEntity.ok(response);
            } else {
                response.put("status", "PasswordFail"); //비밀번호 불일치
                return new ResponseEntity<>(response, HttpStatus.OK);
                //return ResponseEntity.ok(response);
            }
        } else {
            response.put("status", "IdFail"); //존재하지 않는 ID
            return new ResponseEntity<>(response, HttpStatus.OK);
            //return ResponseEntity.ok(response);
        }
    }
}
