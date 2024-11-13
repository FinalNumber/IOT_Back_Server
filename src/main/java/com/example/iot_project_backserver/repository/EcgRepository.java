package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.ECG;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcgRepository extends JpaRepository<ECG, Long>, HealthDataRepository<ECG>{
    boolean existsByUserId(String userId);
}

