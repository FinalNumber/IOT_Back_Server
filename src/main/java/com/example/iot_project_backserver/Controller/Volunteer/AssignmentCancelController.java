package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/assignmentcancel")
public class AssignmentCancelController {

    private final DesiredService desiredService;
    private final VolunteerAssignmentService volunteerAssignmentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> assignmentcancel(@RequestParam("email") String volunteerid,
                                                     @RequestParam("userid") String userid, @RequestParam("notedate") String assignmentdate, @RequestParam("text") String text) {
        desired_volunteer_date savedEntity = desiredService.saveDesiredVolunteerDate(userid, assignmentdate, text);
        volunteerAssignmentService.deleteAssignment(volunteerid, userid, assignmentdate);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", savedEntity);

        return ResponseEntity.ok(response);
    }
}
