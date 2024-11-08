package com.example.iot_project_backserver.repository.Medical;

import com.example.iot_project_backserver.entity.Medical.patient_assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientAssignmentRepository extends JpaRepository<patient_assignment, String> {
    Optional<patient_assignment> findAssignmentByMedicalidAndUserid(String medicalid, String userid);
    boolean existsByMedicalidAndUserid(String medicalid, String userid);
    List<patient_assignment> findByMedicalid(String medicalid);
    void deleteByMedicalidAndUserid(String medicalid, String userid);

    patient_assignment findByMedicalidAndUserid(String medicalid, String userid);
}