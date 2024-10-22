package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/idcheck")
public class Idcheck {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Map<String, String>> idcheck(@RequestParam("email") String email) {
        Map<String, String> response = new HashMap<>();

        // 이메일 ID 값을 콘솔에 출력
        System.out.println("Received email ID: " + email);

        String userid = email;  // 예시로 email을 userId로 사용

        try {
            // ID 중복 여부 체크
            boolean isUsable = checkIdUsability(userid);

            if (isUsable) {
                response.put("status", "success");
            } else {
                response.put("status", "duplication");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "오류가 발생했습니다: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ID 사용 가능 여부를 체크하는 메서드
    private boolean checkIdUsability(String userid) {
        // DB에서 중복되는 user_id가 있는지 확인
        return !userRepository.existsByUserid(userid);
    }
}
