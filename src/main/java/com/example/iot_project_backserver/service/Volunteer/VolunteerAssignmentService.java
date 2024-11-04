package com.example.iot_project_backserver.service.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;

import java.util.List;

public interface VolunteerAssignmentService {
    volunteer_assignment saveVolunteerAssignment(String volunteer_id, String userid, String assignmentdate, String text);
    List<volunteer_assignment> getVolunteerAssignmentsByVolunteerid(String volunteerid);
    List<volunteer_assignment> getVolunteerAssignmentsByUserid(String userid);
    void deleteAssignment(String volunteerid, String userid, String assignmentdate);
}
