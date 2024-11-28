package com.example.iot_project_backserver.Repository.Data.data;

import com.example.iot_project_backserver.Entity.Data.data.GSR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GsrRepository extends JpaRepository<GSR, Long>, HealthDataRepository<GSR>{
    boolean existsByUserid(String userid);
}
