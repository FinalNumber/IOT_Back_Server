package com.example.iot_project_backserver.service;

//import com.example.iot_project_backserver.dto.HealthDataRequestDTO;
import com.example.iot_project_backserver.entity.Airflow;
import com.example.iot_project_backserver.entity.BodyTemp;
import com.example.iot_project_backserver.entity.Eog;
import com.example.iot_project_backserver.exception.CustomException;
import com.example.iot_project_backserver.repository.*;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthDataServiceImpl implements HealthDataService {

    private final AirflowRepository airflowRepository;
    private final BodyTempRepository bodyTempRepository;
    private final EogRepository eogRepository;
    private final EcgRepository ecgRepository;

    private final UserRepository userRepository;

    @Autowired
    public HealthDataServiceImpl(AirflowRepository airflowRepository,
                                 BodyTempRepository bodyTempRepository,
                                 EogRepository eogRepository,
                                 EcgRepository ecgRepository,
                                 UserRepository userRepository) {
        this.airflowRepository = airflowRepository;
        this.bodyTempRepository = bodyTempRepository;
        this.eogRepository = eogRepository;
        this.ecgRepository = ecgRepository;
        this.userRepository = userRepository;
    }
     //아이디 값이 유효한지 판단하기 위한 로직


    @Override
    public Airflow saveAirflow(Airflow airflow) {
        if (!userRepository.existsByUserId(airflow.getUserId())) {
            throw new CustomException("유효하지 않은 사용자 ID 입니다.");
        }
        //TODO 데이터 판단 필요
        return airflowRepository.save(airflow);
    }

    @Override
    public List<Airflow> getAllAirflowData() {
        return airflowRepository.findAll();
    }


    @Override
    public BodyTemp saveBodyTempData(BodyTemp bodyTemp) {
        // 사용자 ID가 유효한지 확인
        if (!userRepository.existsByUserId(bodyTemp.getUserId())) {
            throw new CustomException("유효하지 않은 사용자 ID입니다."); // 유효하지 않은 ID일 경우 보낼 예외 메시지
        }

        Optional<BodyTemp> existingBodyTemp = bodyTempRepository.findOneByUserId(bodyTemp.getUserId());
        //TODO 데이터를 바로 저장하는 것이 아닌 데이터의 수치 판단이 필요
        String pandanStatus = BodyTempStatus(bodyTemp.getBodydata());
        bodyTemp.setPandan(pandanStatus);

        if(existingBodyTemp.isPresent()){
            BodyTemp updatedBodyTemp = existingBodyTemp.get();
            updatedBodyTemp.setBodydata(bodyTemp.getBodydata());
            updatedBodyTemp.setPandan(pandanStatus);
            return bodyTempRepository.save(updatedBodyTemp);
        }else {
            return bodyTempRepository.save(bodyTemp);
        }

        //return bodyTempRepository.save(bodyTemp); //Id가 유효 할 경우 bodyTemp 데이터 저장
    }

    @Override
    public List<BodyTemp> getAllBodyTempData() {
        return bodyTempRepository.findAll();
    }

    @Override
    public Eog saveEogData(Eog eog) {

        if(!userRepository.existsByUserId(eog.getUserId())) {
            throw new CustomException("유효하지 않은 사용자 id 입니다.");
        }
        return eogRepository.save(eog);
    }

    @Override
    public List<Eog> getAllEogData() {
        return eogRepository.findAll();
    }



    // BodyTemp의 수치에 따라 Pandan 상태를 결정하는 메서드
    private String BodyTempStatus(float bodydata) {
        if (bodydata >= 36.5 && bodydata <= 37.5) {
            return "정상";
        } else if (bodydata > 37.5 && bodydata <= 38.0) {
            return "미열";
        } else if (bodydata > 38.0) {
            return "고열";
        } else if (bodydata < 36.0 && bodydata >= 35.5) {
            return "체온낮음";
        } else if (bodydata < 35.5) {
            return "저체온";
        } else {
            return "알 수 없음";
        }
    }




}