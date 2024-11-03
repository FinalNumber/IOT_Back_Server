package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.desired_volunteer_date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesiredVolunteerDateRepository extends JpaRepository<desired_volunteer_date, String> {
}
