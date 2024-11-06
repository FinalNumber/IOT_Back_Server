package com.example.iot_project_backserver.dto.Medical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientAssignmentDTO {
    private String medicalid;
    private String userid;
}