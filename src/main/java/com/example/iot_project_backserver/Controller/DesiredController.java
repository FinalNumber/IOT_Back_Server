package com.example.iot_project_backserver.controller;

import com.example.iot_project_backserver.entity.desired_volunteer_date;
import com.example.iot_project_backserver.service.DesiredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/callvolunteer")
public class DesiredController {

    private final DesiredService desiredService;

    @Autowired
    public DesiredController(DesiredService desiredService) {
        this.desiredService = desiredService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> desired(@RequestParam("email") String userid,
                                                       @RequestParam("noteDate") String desired_date,
                                                       @RequestParam("noteContent") String text) {

        desired_volunteer_date savedEntity = desiredService.saveDesiredVolunteerDate(userid, desired_date, text);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", savedEntity);

        return ResponseEntity.ok(response);
    }
}
