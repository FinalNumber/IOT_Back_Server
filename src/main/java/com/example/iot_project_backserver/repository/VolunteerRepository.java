package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<volunteer, String> {
}
