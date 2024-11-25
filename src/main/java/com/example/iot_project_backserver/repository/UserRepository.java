package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.app_user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<app_user, Long> {
    boolean existsByUserid(String userid); // 사용자 ID 존재 여부 확인
}
