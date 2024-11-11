package com.example.iot_project_backserver.Entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class required_measurements {
    @Id
    private String userid;
    private Boolean airflow;
    private Boolean bodytemp;
    private Boolean nibp;
    private Boolean spo2;
    private Boolean ecg;
    private Boolean emg;
    private Boolean gsr;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private com.example.iot_project_backserver.Entity.User.app_user app_user;
}
