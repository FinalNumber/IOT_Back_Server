package com.example.iot_project_backserver.entity.Volunteer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerAssignmentId implements Serializable {

    private String volunteerid;
    private String userid;

    // equals와 hashCode는 Lombok의 @Data 애노테이션으로 자동 생성됩니다
}
