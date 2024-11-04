package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.dto.CombinedVolunteerData;
import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerAssignmentService;
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
    private final VolunteerAssignmentService volunteerAssignmentService;

    @Autowired
    public VolunteerDesiredController(DesiredService desiredService, VolunteerAssignmentService volunteerAssignmentService) {
        this.desiredService = desiredService;
        this.volunteerAssignmentService = volunteerAssignmentService;
    }

    @GetMapping
    public ResponseEntity<CombinedVolunteerData> getAllVolunteerData() {
        List<desired_volunteer_date> desiredVolunteerDates = desiredService.getAllDesiredVolunteerDates();
        List<volunteer_assignment> volunteerAssignments = volunteerAssignmentService.getAllVolunteerAssignments();

        CombinedVolunteerData combinedData = new CombinedVolunteerData(desiredVolunteerDates, volunteerAssignments);
        return ResponseEntity.ok(combinedData);
    }
}
