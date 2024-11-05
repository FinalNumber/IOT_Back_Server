package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.dto.PatientVolunteerData;
import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patientvolunteercall")
public class PatientDesiredController {

    private final DesiredService desiredService;
    private final VolunteerAssignmentService volunteerAssignmentService;

    @PostMapping
    public ResponseEntity<PatientVolunteerData> callpatient(@RequestParam("userid") String userid) {
        List<desired_volunteer_date> desiredVolunteerDates = desiredService.getDesiredVolunteerDatesByUserid(userid);
        List<volunteer_assignment> volunteerAssignments = volunteerAssignmentService.getVolunteerAssignmentsByUserid(userid);

        PatientVolunteerData responseData = new PatientVolunteerData(desiredVolunteerDates, volunteerAssignments);
        return ResponseEntity.ok(responseData);
    }
}
