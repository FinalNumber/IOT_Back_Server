package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.entity.desired_volunteer_date;
import com.example.iot_project_backserver.service.DesiredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/allcallvolunteer")
public class VolunteerDesiredController {

    private final DesiredService desiredService;

    @Autowired
    public VolunteerDesiredController(DesiredService desiredService) {
        this.desiredService = desiredService;
    }

    @GetMapping
    public ResponseEntity<List<desired_volunteer_date>> getAllDesiredVolunteerDates() {
        List<desired_volunteer_date> desiredVolunteerDates = desiredService.getAllDesiredVolunteerDates();
        return ResponseEntity.ok(desiredVolunteerDates);
    }
}
