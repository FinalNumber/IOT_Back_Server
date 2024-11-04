package com.example.iot_project_backserver.repository.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerAssignmentRepository extends JpaRepository<volunteer_assignment, String> {
    List<volunteer_assignment> findByVolunteerid(String volunteerid);
}
