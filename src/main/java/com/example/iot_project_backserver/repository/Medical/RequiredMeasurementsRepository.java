package com.example.iot_project_backserver.repository.Medical;

import com.example.iot_project_backserver.entity.User.required_measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequiredMeasurementsRepository extends JpaRepository<required_measurements, String> {
}