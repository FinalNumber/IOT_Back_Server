package com.example.iot_project_backserver.Controller.Medical;

import com.example.iot_project_backserver.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/searchpatient")
public class SearchPatientController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> searchpatient(@RequestParam("searchdata") String searchdata) {
        Map<String, Object> response = new HashMap<>();

        if(searchdata.contains("@")){
            // searchdata를 userid로 간주하고 조회
            Optional<Map<String, String>> userInfo = userService.getUserInfoByUserid(searchdata);
            if (userInfo.isPresent()) {
                Map<String, String> userData = userInfo.get(); // Optional 안의 Map을 꺼냄
                response.put("status", "success");
                response.put("userid", userData.get("userid"));
                response.put("name", userData.get("name"));
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "DataEmpty");
                response.put("userid", null);
                response.put("name", null);
                return ResponseEntity.ok(response);
            }
        }else {
            List<Map<String, String>> userInfoList = userService.getUserInfoByName(searchdata);

            if (!userInfoList.isEmpty()) {
                response.put("status", "success");

                // 여러 개의 유저 정보를 리스트로 담아 response에 추가
                List<Map<String, String>> userData = new ArrayList<>();
                for (Map<String, String> userInfo : userInfoList) {
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("userid", userInfo.get("userid"));
                    userMap.put("name", userInfo.get("name"));
                    userData.add(userMap);
                }

                response.put("data", userData);
            } else {
                response.put("status", "DataEmpty");
                response.put("data", null);
            }

            return ResponseEntity.ok(response);
        }
    }
}
