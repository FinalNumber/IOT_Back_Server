package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.entity.app_user;
import com.example.iot_project_backserver.entity.volunteer;
import com.example.iot_project_backserver.service.UserService;
import com.example.iot_project_backserver.service.VolunteerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder; // 추가된 임포트

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private VolunteerService volunteerService; // VolunteerService 주입

    @Autowired
    private PasswordEncoder passwordEncoder; // PasswordEncoder 주입

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

        // user 엔티티 생성 및 값 설정
        app_user newUser = new app_user();
        newUser.setUserid(email);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword); // 암호화된 비밀번호 설정

        newUser.setName(username);
        newUser.setBirth(birth);
        newUser.setPhone_num(phoneNum);
        newUser.setDivision(role);

        // DB에 사용자 정보 저장
        app_user savedUser = userService.saveUser(newUser);

        // role이 volunteer인 경우 volunteer 테이블에도 저장
        if ("volunteer".equalsIgnoreCase(role)) {
            volunteer newVolunteer = new volunteer();
            newVolunteer.setVolunteer_id(userid);
            newVolunteer.setVolunteer_time(0); // 초기 값 설정
            volunteerService.saveVolunteer(newVolunteer); // VolunteerService를 통해 저장
        }

        // 응답을 위한 맵 생성
        response.put("status", "success");
        response.put("email", email);
        response.put("username", username);
        response.put("birth", birth);
        response.put("phoneNum", phoneNum);
        response.put("role", role);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
