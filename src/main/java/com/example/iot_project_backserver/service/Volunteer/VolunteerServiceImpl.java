package com.example.iot_project_backserver.service.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.volunteer;
import com.example.iot_project_backserver.repository.Volunteer.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public volunteer saveVolunteer(volunteer newVolunteer) {
        return volunteerRepository.save(newVolunteer);
    }
}
