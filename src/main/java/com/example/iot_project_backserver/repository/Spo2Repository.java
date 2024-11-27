package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.SPO2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Spo2Repository extends JpaRepository<SPO2,Long>, HealthDataRepository<SPO2> {
    boolean existsByUserId(String userId);
}