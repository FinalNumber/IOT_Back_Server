package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/callpatient")
public class PatientDesiredController {

    private final DesiredService desiredService;

    @Autowired
    public PatientDesiredController(DesiredService desiredService) {
        this.desiredService = desiredService;
    }

    @PostMapping
    public ResponseEntity<List<desired_volunteer_date>> callpatient(@RequestParam("email") String userid) {
        // userid에 해당하는 모든 데이터 조회
        List<desired_volunteer_date> desiredVolunteerDates = desiredService.getDesiredVolunteerDatesByUserid(userid);

        return ResponseEntity.ok(desiredVolunteerDates);
    }
}
