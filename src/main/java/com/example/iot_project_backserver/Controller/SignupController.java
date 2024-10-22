package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.entity.app_user;
import com.example.iot_project_backserver.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createNewUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("username") String username,
            @RequestParam("birth") String birth,
            @RequestParam("phoneNum") String phoneNum,
            @RequestParam("role") String role,
            @RequestParam(value = "userImage", required = false) MultipartFile userImage) {

        Map<String, String> response = new HashMap<>();

        String userid = email;

        // 아이디 중복 체크
        if (userService.existsByUserid(userid)) {
            response.put("status", "error");
            response.put("message", "이미 존재하는 아이디입니다.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // userImage는 MultipartFile 타입이므로 추가적인 처리가 필요할 수 있음
        if (userImage != null) {
            String userImageName = userImage.getOriginalFilename(); // 이미지 파일 이름
            System.out.println("Uploaded Image Name: " + userImageName);
        }

        // user 엔티티 생성 및 값 설정
        app_user newUser = new app_user();
        newUser.setUserid(email);
        newUser.setPassword(password);
        newUser.setName(username);
        newUser.setBirth(birth);
        newUser.setPhone_num(phoneNum);
        newUser.setDivision(role);

        // DB에 사용자 정보 저장
        app_user savedUser = userService.saveUser(newUser);

        // 응답을 위한 맵 생성
        response.put("message", "User created successfully");
        response.put("email", email);
        response.put("username", username);
        response.put("birth", birth);
        response.put("phoneNum", phoneNum);
        response.put("role", role);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
