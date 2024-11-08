package com.example.iot_project_backserver.Controller.Medical;

import com.example.iot_project_backserver.entity.Medical.patient_assignment;
import com.example.iot_project_backserver.service.Medical.PatientAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loadpatient")
public class LoadPatientController {

    @Autowired
    private PatientAssignmentService patientAssignmentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> loadpatient(@RequestParam("medicalid") String medicalid) {
        List<patient_assignment> assignments = patientAssignmentService.findAssignmentsByMedicalid(medicalid);

        Map<String, Object> response = new HashMap<>();
        if (!assignments.isEmpty()) {
            response.put("status", "success");

            response.put("data", assignments.stream().map(assignment -> {
                Map<String, Object> assignmentData = new HashMap<>();
                assignmentData.put("medicalid", assignment.getMedicalid());
                assignmentData.put("userid", assignment.getUserid());

                // app_user 정보 포함
                if (assignment.getApp_user() != null) {
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("userid", assignment.getApp_user().getUserid());
                    userData.put("name", assignment.getApp_user().getName());
                    userData.put("birth", assignment.getApp_user().getBirth());

                    assignmentData.put("app_user", userData);
                } else {
                    assignmentData.put("app_user", null);
                }

                return assignmentData;
            }).collect(Collectors.toList()));
        } else {
            response.put("status", "DataEmpty");
            response.put("data", null);
        }

        return ResponseEntity.ok(response);
    }
}
