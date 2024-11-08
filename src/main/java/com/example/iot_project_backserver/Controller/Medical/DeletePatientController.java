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
@RequestMapping("/deletepatient")
public class DeletePatientController {

    @Autowired
    private PatientAssignmentService patientAssignmentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> deletepatient(@RequestParam("medicalid") String medicalid, @RequestParam("userid") String userid) {
        Map<String, Object> response = new HashMap<>();
        boolean isDeleted = patientAssignmentService.deletePatientAssignment(medicalid, userid);
        response.put("success", isDeleted);
        response.put("message", isDeleted ? "Record deleted successfully." : "Record not found.");
        return ResponseEntity.ok(response);
    }
}
