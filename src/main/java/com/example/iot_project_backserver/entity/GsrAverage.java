package com.example.iot_project_backserver.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class GsrAverage {
    private Float averageValue;
    private String userid;
}