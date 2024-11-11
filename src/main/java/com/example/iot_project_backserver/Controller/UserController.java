package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.Entity.Medical.patient_assignment;
import com.example.iot_project_backserver.Entity.User.app_user;
import com.example.iot_project_backserver.Entity.Volunteer.volunteer;
import com.example.iot_project_backserver.Repository.Volunteer.VolunteerRepository;
import com.example.iot_project_backserver.Service.MedicalService;
import com.example.iot_project_backserver.Service.UserService;
import com.example.iot_project_backserver.Service.VolunteerService;
import com.example.iot_project_backserver.Security.Config.Jwt.TokenProvider;
import com.example.iot_project_backserver.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicalService medicalService;
    // 이메일 중복 체크
    @PostMapping("/idcheck")
    public ResponseEntity<Map<String, String>> idcheck(@RequestParam("email") String email) {
        Map<String, String> response = new HashMap<>();
        System.out.println("Received email ID: " + email);
        String userid = email;

        try {
            boolean isUsable = !userRepository.existsByUserid(userid);
            response.put("status", isUsable ? "success" : "duplication");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "오류가 발생했습니다: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam("email") String email,
                                                     @RequestParam("password") String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<app_user> userOptional = userService.getUserById(email);

        if (userOptional.isPresent()) {
            app_user user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String accessToken = tokenProvider.generateAccessToken(user);
                String refreshToken = tokenProvider.generateRefreshToken(user);

                user.setRefresh_token(refreshToken);
                userService.saveUser(user);

                Map<String, String> responseData = new HashMap<>();
                responseData.put("accessToken", accessToken);
                responseData.put("refreshToken", refreshToken);
                responseData.put("userid", user.getUserid());
                responseData.put("name", user.getName());
                responseData.put("birth", user.getBirth());
                responseData.put("phone_num", user.getPhone_num());
                responseData.put("division", user.getDivision());

                response.put("status", "success");
                response.put("data", responseData);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "PasswordFail");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.put("status", "IdFail");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    // 회원가입 처리
    @PostMapping("/signup")
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

        if (userService.existsByUserid(userid)) {
            response.put("status", "error");
            response.put("message", "이미 존재하는 아이디입니다.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        app_user newUser = new app_user();
        newUser.setUserid(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setName(username);
        newUser.setBirth(birth);
        newUser.setPhone_num(phoneNum);
        newUser.setDivision(role);

        app_user savedUser = userService.saveUser(newUser);

        if ("volunteer".equalsIgnoreCase(role)) {
            volunteer newVolunteer = new volunteer();
            newVolunteer.setVolunteerid(userid);
            newVolunteer.setVolunteertime(0);
            volunteerService.saveVolunteer(newVolunteer);
        }

        response.put("status", "success");
        response.put("email", email);
        response.put("username", username);
        response.put("birth", birth);
        response.put("phoneNum", phoneNum);
        response.put("role", role);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/medicalname")
    public ResponseEntity<Map<String, String>> medicalname(@RequestParam("userid") String userid) {
        // patient_assignment 테이블에서 userid에 해당하는 레코드를 조회
        Optional<patient_assignment> assignment = medicalService.findByUserid(userid);
        Map<String, String> response = new HashMap<>();
        if (assignment.isPresent()) {
            // medicalid를 사용하여 app_user 테이블에서 의료 정보 조회
            Optional<app_user> medicalUser = userService.findUserByUserid(assignment.get().getMedicalid());

            if (medicalUser != null) {
                response.put("userid", medicalUser.get().getUserid());
                response.put("name", medicalUser.get().getName());
                // 필요한 다른 정보도 추가 가능

                return ResponseEntity.ok(response);
            } else {
                response.put("status", "null");
                return ResponseEntity.ok(response);
            }
        }else{
            response.put("status", "null");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/tokencheck")
    public ResponseEntity<Map<String, String>> tokencheck(@RequestParam("userid") String userid, @RequestParam("refreshToken") String refreshToken) {

        // 리프레시 토큰 검증
        boolean isTokenValid = volunteerService.validateRefreshToken(userid, refreshToken);
        System.out.println("Is token valid: " + isTokenValid);

        Map<String, String> response = new HashMap<>();
        if (!isTokenValid) {
            response.put("status", "TokenInvalid");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
