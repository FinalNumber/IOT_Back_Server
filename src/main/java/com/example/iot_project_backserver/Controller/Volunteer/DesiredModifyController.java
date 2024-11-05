package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/callvolunteermodify")
public class DesiredModifyController {

    private final DesiredService desiredService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> callvolunteermodify(@RequestParam("email") String userid,
                                                       @RequestParam("notedate") String desireddate,
                                                       @RequestParam("text") String text) {

        boolean isUpdated = desiredService.updateDesiredVolunteerDate(userid, desireddate, text);

        Map<String, Object> response = new HashMap<>();
        if (isUpdated) {
            response.put("status", "success");
            response.put("message", "Record updated successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "failure");
            response.put("message", "Record not found.");
            return ResponseEntity.status(404).body(response);
        }
    }
}
