package com.example.iot_project_backserver.repository;


import com.example.iot_project_backserver.entity.GSR_Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface GSR_ResultRepository extends JpaRepository<GSR_Result, Long> {
    Optional<GSR_Result> findByUseridAndDate(String userid, Date dates);
}
