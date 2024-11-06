package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.service.Volunteer.VolunteerAssignmentService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/volunteercomplete")
public class VolunteerCPController {

    private final VolunteerAssignmentService volunteerAssignmentService;
    private final VolunteerService volunteerService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> volunteerComplete(
            @RequestParam("volunteerid") String volunteerid,
            @RequestParam("userid") String userid,
            @RequestParam("assignmentdate") String assignmentdate) {

        volunteerAssignmentService.deleteAssignment(volunteerid, userid, assignmentdate);

        // volunteer 테이블에서 volunteer_time 증가
        volunteerService.incrementVolunteertime(volunteerid);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Assignment record deleted successfully.");

        return ResponseEntity.ok(response);
    }
}