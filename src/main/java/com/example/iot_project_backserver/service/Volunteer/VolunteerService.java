package com.example.iot_project_backserver.service.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.volunteer;

import java.util.Optional;

public interface VolunteerService {
    volunteer saveVolunteer(volunteer newVolunteer);
    void incrementVolunteertime(String volunteerid);
    Optional<Integer> getVolunteerTimeById(String volunteerid);
}
