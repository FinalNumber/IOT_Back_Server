package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.app_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<app_user, String> {
    // 추가적인 사용자 정의 메소드가 필요한 경우 여기서 선언할 수 있습니다.
    boolean existsByUserid(String userid);
}
