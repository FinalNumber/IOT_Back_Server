package com.example.iot_project_backserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class volunteer_assignment {
    @Id
    private String volunteer_id;
    @Id
    private String userid;
    private String assignment_date;
}
