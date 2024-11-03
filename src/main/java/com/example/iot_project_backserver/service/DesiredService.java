package com.example.iot_project_backserver.service;

import com.example.iot_project_backserver.entity.desired_volunteer_date;
import com.example.iot_project_backserver.repository.DesiredVolunteerDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesiredService {

    private final DesiredVolunteerDateRepository desiredVolunteerDateRepository;

    @Autowired
    public DesiredService(DesiredVolunteerDateRepository desiredVolunteerDateRepository) {
        this.desiredVolunteerDateRepository = desiredVolunteerDateRepository;
    }

    public desired_volunteer_date saveDesiredVolunteerDate(String userid, String desired_date, String text) {
        desired_volunteer_date desiredVolunteerDate = desired_volunteer_date.builder()
                .userid(userid)
                .desired_date(desired_date)
                .text(text)
                .build();

        return desiredVolunteerDateRepository.save(desiredVolunteerDate);
    }

    public List<desired_volunteer_date> getAllDesiredVolunteerDates() {
        return desiredVolunteerDateRepository.findAll();
    }

    public List<desired_volunteer_date> getDesiredVolunteerDatesByUserid(String userid) {
        return desiredVolunteerDateRepository.findByUserid(userid);
    }
}
