package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.desired_volunteer_date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesiredVolunteerDateRepository extends JpaRepository<desired_volunteer_date, String> {
    // 특정 userid와 일치하는 모든 desired_volunteer_date 데이터 조회
    List<desired_volunteer_date> findByUserid(String userid);
}
