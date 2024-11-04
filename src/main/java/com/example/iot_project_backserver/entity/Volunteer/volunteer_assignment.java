package com.example.iot_project_backserver.entity.Volunteer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@IdClass(VolunteerAssignmentId.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class volunteer_assignment {
    @Id
    private String volunteerid;
    @Id
    private String userid;
    private String assignment_date;
    private String text;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private com.example.iot_project_backserver.entity.app_user userByUserid;

    @ManyToOne
    @JoinColumn(name = "volunteerid", referencedColumnName = "userid", insertable = false, updatable = false)
    private com.example.iot_project_backserver.entity.app_user userByVolunteerId;
}
