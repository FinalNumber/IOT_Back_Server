package com.example.iot_project_backserver.Service;

import com.example.iot_project_backserver.Entity.User.required_measurements;
import com.example.iot_project_backserver.Entity.Medical.patient_assignment;

import java.util.List;

public interface MedicalService {
    // LoadMeasureService의 메서드
    required_measurements checkAndInsert(String userid);
    required_measurements updateMeasurements(String userid, boolean airflow, boolean bodytemp, boolean nibp, boolean spo2, boolean ecg, boolean emg, boolean gsr);

    // PatientAssignmentService의 메서드
    boolean checkAssignmentExists(String medicalid, String userid);
    patient_assignment saveAssignment(String medicalid, String userid);
    List<patient_assignment> findAssignmentsByMedicalid(String medicalid);
    boolean deletePatientAssignment(String medicalid, String userid);
}
