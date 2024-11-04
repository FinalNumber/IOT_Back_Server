package com.example.iot_project_backserver.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/volunteerassignment")
public class VolunteerAssignmentController {
    @PostMapping
    public ResponseEntity<Map<String, Object>> volunteerassignment(@RequestParam("email") String volunteer_id,
                                                     @RequestParam("patient_id") String userid, @RequestParam("assignment_date") String assignment_date) {

        return null;
    }
}
