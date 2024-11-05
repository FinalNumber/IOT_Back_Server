package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.dto.CombinedVolunteerData;
import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/allvolunteercall")
public class VolunteerDesiredController {

    private final DesiredService desiredService;
    private final VolunteerAssignmentService volunteerAssignmentService;

    @PostMapping
    public ResponseEntity<CombinedVolunteerData> postAllVolunteerData(@RequestParam("volunteerid") String volunteerid) {
        List<desired_volunteer_date> desiredVolunteerDates = desiredService.getAllDesiredVolunteerDates();
        List<volunteer_assignment> volunteerAssignments = volunteerAssignmentService.getVolunteerAssignmentsByVolunteerid(volunteerid);


        CombinedVolunteerData combinedData = new CombinedVolunteerData(desiredVolunteerDates, volunteerAssignments);
        return ResponseEntity.ok(combinedData);
    }
}
