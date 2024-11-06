package com.example.iot_project_backserver.dto.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientVolunteerData {
    private List<desired_volunteer_date> desiredVolunteerDates;
    private List<volunteer_assignment> volunteerAssignments;
}
