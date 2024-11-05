package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/volunteerassignment")
public class VolunteerAssignmentController {

    private final VolunteerAssignmentService volunteerAssignmentService;
    private final DesiredService desiredService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> volunteerassignment(
            @RequestParam("volunteerid") String volunteerid,
            @RequestParam("userid") String userid,
            @RequestParam("assignmentdate") String assignmentdate, @RequestParam("text") String text) {

        volunteer_assignment savedAssignment = volunteerAssignmentService.saveVolunteerAssignment(volunteerid, userid, assignmentdate, text);

        desiredService.deleteDesiredVolunteerDateByUseridAndDate(userid, assignmentdate);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", savedAssignment);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
