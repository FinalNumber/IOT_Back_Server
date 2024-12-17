package com.example.iot_project_backserver.Entity.Data.Result;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class SPO2_Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성
    private Long id;

    private String userid;

    private String SPO2Result;
    @Temporal(TemporalType.DATE)
    private Date date;
}