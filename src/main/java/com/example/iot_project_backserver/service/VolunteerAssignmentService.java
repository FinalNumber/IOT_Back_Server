package com.example.iot_project_backserver.service;

import com.example.iot_project_backserver.entity.volunteer_assignment;

import java.util.List;

public interface VolunteerAssignmentService {
    volunteer_assignment saveVolunteerAssignment(String volunteer_id, String userid, String assignment_date);
    List<volunteer_assignment> getAllVolunteerAssignments();
}
