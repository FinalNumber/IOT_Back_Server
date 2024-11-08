package com.example.iot_project_backserver.service.Medical;

import com.example.iot_project_backserver.entity.User.required_measurements;

public interface LoadMeasureService {
    required_measurements checkAndInsert(String userid);
    required_measurements updateMeasurements(String userid, boolean airflow, boolean bodytemp, boolean nibp, boolean spo2, boolean ecg, boolean emg, boolean gsr);
}