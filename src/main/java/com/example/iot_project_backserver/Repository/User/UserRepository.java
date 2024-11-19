package com.example.iot_project_backserver.Repository.User;

import com.example.iot_project_backserver.Entity.User.app_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<app_user, String> {
    // 추가적인 사용자 정의 메소드가 필요한 경우 여기서 선언할 수 있습니다.
    boolean existsByUserid(String userid);
    List<app_user> findByName(String name);

    Optional<app_user> findByUserid(String volunteerid);
}
