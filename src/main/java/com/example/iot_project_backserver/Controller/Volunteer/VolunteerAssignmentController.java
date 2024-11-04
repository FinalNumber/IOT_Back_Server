package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/volunteerassignment")
public class VolunteerAssignmentController {

    private final VolunteerAssignmentService volunteerAssignmentService;
    private final DesiredService desiredService;

    @Autowired
    public VolunteerAssignmentController(VolunteerAssignmentService volunteerAssignmentService, DesiredService desiredService) {
        this.volunteerAssignmentService = volunteerAssignmentService;
        this.desiredService = desiredService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> volunteerassignment(
            @RequestParam("email") String volunteer_id,
            @RequestParam("userid") String userid,
            @RequestParam("notedate") String assignment_date) {

        volunteer_assignment savedAssignment = volunteerAssignmentService.saveVolunteerAssignment(volunteer_id, userid, assignment_date);

        desiredService.deleteDesiredVolunteerDateByUseridAndDate(userid, assignment_date);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", savedAssignment);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
