package com.example.iot_project_backserver.Controller.Medical;

import com.example.iot_project_backserver.service.Medical.PatientAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/assignmentpatient")
public class PatientAssignmentController {

    @Autowired
    private PatientAssignmentService patientAssignmentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> assignmentpatient(@RequestParam("medicalid") String medicalid, @RequestParam("userid") String userid) {
        Map<String, Object> response = new HashMap<>();

        // 서비스에서 레코드 존재 여부 확인
        boolean exists = patientAssignmentService.checkAssignmentExists(medicalid, userid);
        if (exists) {
            // 이미 존재하는 경우
            response.put("status", "duplication");
        } else {
            // 존재하지 않는 경우 서비스에서 새로운 레코드 저장
            patientAssignmentService.saveAssignment(medicalid, userid);
            response.put("status", "success");
        }

        return ResponseEntity.ok(response);
    }
}
