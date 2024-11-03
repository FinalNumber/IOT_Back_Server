package com.example.iot_project_backserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@IdClass(DesiredVolunteerDateId.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class desired_volunteer_date {

    @Id
    private String userid;
    @Id
    private String desired_date;
    private String text;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private app_user app_user;
}
