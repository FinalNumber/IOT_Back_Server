package com.example.iot_project_backserver.service;


import com.example.iot_project_backserver.entity.Airflow;
import com.example.iot_project_backserver.entity.BodyTemp;
import com.example.iot_project_backserver.entity.Eog;
import com.example.iot_project_backserver.entity.ECG;

import java.util.List;

public interface HealthDataService {
    //Airflow
    Airflow saveAirflow(Airflow airflow);
    List<Airflow> getAllAirflowData();

    //BodyTemp
    BodyTemp saveBodyTempData(BodyTemp bodyTemp);
    List<BodyTemp> getAllBodyTempData();

    // Eog 관련 메서드
    Eog saveEogData(Eog eog);
    List<Eog> getAllEogData();

    //Ecg saveEcgData(Ecg ecg);
}

