package com.example.iot_project_backserver.service;

import com.example.iot_project_backserver.entity.app_user;
import com.example.iot_project_backserver.entity.desired_volunteer_date;
import com.example.iot_project_backserver.repository.DesiredVolunteerDateRepository;
import com.example.iot_project_backserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesiredService {

    private final DesiredVolunteerDateRepository desiredVolunteerDateRepository;
    private final UserRepository userRepository;

    @Autowired
    public DesiredService(DesiredVolunteerDateRepository desiredVolunteerDateRepository, UserRepository userRepository) {
        this.desiredVolunteerDateRepository = desiredVolunteerDateRepository;
        this.userRepository = userRepository;
    }

    public desired_volunteer_date saveDesiredVolunteerDate(String userid, String desireddate, String text) {
        Optional<app_user> appUserOptional = userRepository.findById(userid);

        if (appUserOptional.isPresent()) {
            desired_volunteer_date desiredVolunteerDate = desired_volunteer_date.builder()
                    .userid(userid)
                    .desireddate(desireddate)
                    .text(text)
                    .app_user(appUserOptional.get()) // 올바른 변수명 사용
                    .build();

            return desiredVolunteerDateRepository.save(desiredVolunteerDate);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userid);
        }
    }

    public List<desired_volunteer_date> getAllDesiredVolunteerDates() {
        return desiredVolunteerDateRepository.findAll();
    }

    public List<desired_volunteer_date> getDesiredVolunteerDatesByUserid(String userid) {
        return desiredVolunteerDateRepository.findByUserid(userid);
    }

    @Transactional
    public void deleteDesiredVolunteerDateByUseridAndDate(String userid, String desireddate) {
        desiredVolunteerDateRepository.deleteByUseridAndDesireddate(userid, desireddate);
    }
}
