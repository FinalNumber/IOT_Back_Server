package com.example.iot_project_backserver.service.Medical;

import com.example.iot_project_backserver.entity.Medical.patient_assignment;
import com.example.iot_project_backserver.repository.Medical.PatientAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientAssignmentServiceImpl implements PatientAssignmentService {

    @Autowired
    private PatientAssignmentRepository patientAssignmentRepository;

    @Override
    public boolean checkAssignmentExists(String medicalid, String userid) {
        return patientAssignmentRepository.existsByMedicalidAndUserid(medicalid, userid);
    }

    @Override
    public patient_assignment saveAssignment(String medicalid, String userid) {
        patient_assignment newAssignment = patient_assignment.builder()
                .medicalid(medicalid)
                .userid(userid)
                .build();
        return patientAssignmentRepository.save(newAssignment);
    }
    @Override
    public List<patient_assignment> findAssignmentsByMedicalid(String medicalid) {
        return patientAssignmentRepository.findByMedicalid(medicalid);
    }

    @Override
    public boolean deletePatientAssignment(String medicalid, String userid) {
        patient_assignment assignment = patientAssignmentRepository.findByMedicalidAndUserid(medicalid, userid);
        if (assignment != null) {
            patientAssignmentRepository.delete(assignment);
            return true;
        }
        return false;
    }
}
