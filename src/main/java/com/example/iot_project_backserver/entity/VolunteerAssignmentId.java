package com.example.iot_project_backserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerAssignmentId implements Serializable {

    private String volunteer_id;
    private String userid;

    // equals와 hashCode는 Lombok의 @Data 애노테이션으로 자동 생성됩니다
}
