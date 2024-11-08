package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.dto.Volunteer.CombinedVolunteerData;
import com.example.iot_project_backserver.service.Volunteer.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/volunteertime")
public class VolunteerTimeController {

    @Autowired
    private VolunteerService volunteerService;

    @PostMapping
    public ResponseEntity<Map<String, String>> volunteertime(@RequestParam("volunteerid") String volunteerid) {
        Optional<Integer> volunteerTime = volunteerService.getVolunteerTimeById(volunteerid);
        Map<String, String> response = new HashMap<>();

        if (volunteerTime.isPresent()) {
            response.put("volunteertime", volunteerTime.get().toString());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Volunteer not found");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
