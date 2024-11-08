package com.example.iot_project_backserver.service.Medical;

import com.example.iot_project_backserver.entity.Medical.patient_assignment;

import java.util.List;
import java.util.Optional;

public interface PatientAssignmentService {
    boolean checkAssignmentExists(String medicalid, String userid);
    patient_assignment saveAssignment(String medicalid, String userid);
    List<patient_assignment> findAssignmentsByMedicalid(String medicalid);

    boolean deletePatientAssignment(String medicalid, String userid);
}
