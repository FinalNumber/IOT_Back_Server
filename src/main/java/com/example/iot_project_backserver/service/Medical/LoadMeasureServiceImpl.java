package com.example.iot_project_backserver.service.Medical;

import com.example.iot_project_backserver.entity.User.required_measurements;
import com.example.iot_project_backserver.repository.Medical.RequiredMeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoadMeasureServiceImpl implements LoadMeasureService {

    @Autowired
    private RequiredMeasurementsRepository requiredMeasurementsRepository;

    @Override
    public required_measurements checkAndInsert(String userid) {
        Optional<required_measurements> existingRecord = requiredMeasurementsRepository.findById(userid);

        if (existingRecord.isEmpty()) {
            required_measurements newRecord = required_measurements.builder()
                    .userid(userid)
                    .airflow(false)    // 기본값 설정
                    .bodytemp(false)   // 기본값 설정
                    .nibp(false)       // 기본값 설정
                    .spo2(false)       // 기본값 설정
                    .ecg(false)        // 기본값 설정
                    .emg(false)        // 기본값 설정
                    .gsr(false)        // 기본값 설정
                    .build();
            return requiredMeasurementsRepository.save(newRecord);
        }
        return existingRecord.get();
    }

    @Override
    public required_measurements updateMeasurements(String userid, boolean airflow, boolean bodytemp, boolean nibp, boolean spo2, boolean ecg, boolean emg, boolean gsr) {
        Optional<required_measurements> existingRecord = requiredMeasurementsRepository.findById(userid);

        if (existingRecord.isPresent()) {
            required_measurements measurements = existingRecord.get();
            measurements.setAirflow(airflow);
            measurements.setBodytemp(bodytemp);
            measurements.setNibp(nibp);
            measurements.setSpo2(spo2);
            measurements.setEcg(ecg);
            measurements.setEmg(emg);
            measurements.setGsr(gsr);
            return requiredMeasurementsRepository.save(measurements);
        }
        return null;  // or throw an exception if record not found
    }
}