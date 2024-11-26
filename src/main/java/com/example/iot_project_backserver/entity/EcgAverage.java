package com.example.iot_project_backserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class EcgAverage {
    @Column(name = "averagevalue")
    private Float averageValue;
    @Column(name = "userid")
    private String userId;

}
