package com.example.iot_project_backserver.repository;

import com.example.iot_project_backserver.entity.desired_volunteer_date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesiredVolunteerDateRepository extends JpaRepository<desired_volunteer_date, String> {
    List<desired_volunteer_date> findByUserid(String userid); // 사용자 ID로 검색
    void deleteByUseridAndDesireddate(String userid, String desireddate); // 정확한 필드명을 반영한 삭제 메서드
}
