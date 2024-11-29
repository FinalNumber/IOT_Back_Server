package com.example.iot_project_backserver.Service;

import com.example.iot_project_backserver.Entity.Data.data.*;
import com.example.utils.ExcelGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelDataServiceServiceImpl implements ModelDataService {


    /*@Override
    public void createECGDataCSV(ECG ecg) {
        String fileName = "C:\\Users\\sunmoon\\IdeaProjects\\IoT_Back_Server\\ECG_Data.xlsx";

        // 헤더 정의
        List<String> headers = new ArrayList<>();
        headers.add("userid");
        for (double i = 0.002; i <= 30.000; i += 0.002) {
            headers.add(String.format("%.3f", i));
        }

        // 데이터 준비
        List<Object> row = new ArrayList<>();
        row.add(ecg.getUserid());
        row.addAll(ecg.getEcgdata());

        // 엑셀 파일 생성/추가
        ExcelGenerator.addOrCreateExcelFile(fileName, headers, row);
    }*/

    @Override
    public void createECGDataCSV(ECG ecg) {
        if (ecg == null || ecg.getUserid() == null) {
            throw new IllegalArgumentException("ECG 객체 또는 사용자 ID가 null입니다.");
        }

        String fileName = "C:\\Users\\sunmoon\\IdeaProjects\\IoT_Back_Server\\ECG_Data.xlsx";

        // 헤더 정의
        List<String> headers = new ArrayList<>();
        headers.add("userid");
        for (double i = 0.002; i <= 30.000; i += 0.002) {
            headers.add(String.format("%.3f", i));
        }

        // 데이터 준비
        List<Object> row = new ArrayList<>();
        row.add(ecg.getUserid());

        if (ecg.getEcgdata() != null && !ecg.getEcgdata().isEmpty()) {
            row.addAll(ecg.getEcgdata());
        } else {
            // ecgdata가 null이거나 비어있는 경우 기본 값(예: 0.0)으로 채우기
            for (int i = 0; i < headers.size() - 1; i++) {
                row.add(0.0);  // 기본 값으로 0.0을 추가
            }
        }

        // 엑셀 파일 생성/추가
        ExcelGenerator.addOrCreateExcelFile(fileName, headers, row);
    }



    @Override
    public void createAirflowDataCSV(Airflow airflow) {
        String fileName = "C:\\Users\\sunmoon\\IdeaProjects\\IoT_Back_Server\\Airflow_Data.xlsx";

        // 헤더 정의
        List<String> headers = new ArrayList<>();
        headers.add("userid");
        for (double i = 0.002; i <= 30.000; i += 0.002) {
            headers.add(String.format("%.3f", i));
        }

        // 데이터 준비
        List<Object> row = new ArrayList<>();
        row.add(airflow.getUserid());
        row.addAll(airflow.getAirflowdata());

        // 엑셀 파일 생성/추가
        ExcelGenerator.addOrCreateExcelFile(fileName, headers, row);
    }

    @Override
    public void createEOGDataCSV(EOG eog) {
        String fileName = "C:\\Users\\sunmoon\\IdeaProjects\\IoT_Back_Server\\EOG_Data.xlsx";

        // 헤더 정의
        List<String> headers = new ArrayList<>();
        headers.add("userid");
        for (double i = 0.002; i <= 30.000; i += 0.002) {
            headers.add(String.format("%.3f", i));
        }

        // 데이터 준비
        List<Object> row = new ArrayList<>();
        row.add(eog.getUserid());
        row.addAll(eog.getEogdata());

        // 엑셀 파일 생성/추가
        ExcelGenerator.addOrCreateExcelFile(fileName, headers, row);
    }

    @Override
    public void createEMGDataCSV(EMG emg) {
        String fileName = "C:\\Users\\sunmoon\\IdeaProjects\\IoT_Back_Server\\EMG_Data.xlsx";

        // 헤더 정의
        List<String> headers = new ArrayList<>();
        headers.add("userid");
        for (double i = 0.002; i <= 30.000; i += 0.002) {
            headers.add(String.format("%.3f", i));
        }

        // 데이터 준비
        List<Object> row = new ArrayList<>();
        row.add(emg.getUserid());
        row.addAll(emg.getEmgdata());

        // 엑셀 파일 생성/추가
        ExcelGenerator.addOrCreateExcelFile(fileName, headers, row);
    }


    @Override
    public void createGSRDataCSV(GSR gsr) {
        String fileName = "C:\\Users\\sunmoon\\IdeaProjects\\IoT_Back_Server\\GSR_Data.xlsx";

        // 헤더 정의
        List<String> headers = new ArrayList<>();
        headers.add("userid");
        for (double i = 0.002; i <= 30.000; i += 0.002) {
            headers.add(String.format("%.3f", i));
        }

        // 데이터 준비
        List<Object> row = new ArrayList<>();
        row.add(gsr.getUserid());
        row.addAll(gsr.getGsrdata());

        // 엑셀 파일 생성/추가
        ExcelGenerator.addOrCreateExcelFile(fileName, headers, row);
    }


}
