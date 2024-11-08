package com.example.iot_project_backserver.dto;

import com.example.iot_project_backserver.entity.Volunteer.desired_volunteer_date;
import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CombinedVolunteerData {
    private List<desired_volunteer_date> desiredVolunteerDates;
    private List<volunteer_assignment> volunteerAssignments;
}