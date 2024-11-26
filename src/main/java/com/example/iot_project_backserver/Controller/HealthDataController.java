package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.entity.*;
import com.example.iot_project_backserver.service.HealthDataService;
import com.example.iot_project_backserver.service.ModelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.iot_project_backserver.service.ModelData;
import org.springframework.web.client.RestTemplate;

@Controller
@RestController
@RequestMapping("/ws")
public class HealthDataController {

    private final HealthDataService healthDataService;
    //임시 데이터 위함

    @Autowired
    public HealthDataController(HealthDataService healthDataService) {
        this.healthDataService = healthDataService;
    }

    @Autowired
    public ModelDataService modelDataService;

    @PostMapping("/airflow")
    public ResponseEntity<String> saveAirflow(@RequestBody Airflow airflow) { //TODO 이후 valid 이용해서 검증 기증 추가하기
        modelDataService.createAirflowDataCSV(airflow);
        healthDataService.processAndSaveAirflowData(airflow);
        return ResponseEntity.ok("Airflow data processed and saved successfully.");
    }

    @GetMapping("/airflow")
    public ResponseEntity<List<Airflow>> getAllAirflowData() {
        return ResponseEntity.ok(healthDataService.getAllAirflowData());
    }

    @PostMapping("/bodytemp")
    public ResponseEntity<BodyTemp> saveBodyTemp(@RequestBody BodyTemp bodyTemp) {
        return ResponseEntity.ok(healthDataService.saveBodyTempData(bodyTemp));
    }

    @GetMapping("/bodytemp")
    public ResponseEntity<List<BodyTemp>> getAllBodyTempData() {
        return ResponseEntity.ok(healthDataService.getAllBodyTempData());
    }

    @PostMapping("/nibp")
    public ResponseEntity<NIBP> saveNIBP(@RequestBody NIBP nibp) {
        return ResponseEntity.ok(healthDataService.saveNIBPData(nibp));
    }

    @PostMapping("/spo2")
    public ResponseEntity<SPO2> saveSPO2(@RequestBody SPO2 spo2) {
        return ResponseEntity.ok(healthDataService.saveSPO2(spo2));
    }

    @PostMapping("/eog")
    public ResponseEntity<String> saveECGData(@RequestBody EOG eog) {
        modelDataService.createEOGDataCSV(eog);
        healthDataService.processAndSaveEOGData(eog);
        return ResponseEntity.ok("EOG data processed and saved successfully.");
    }

    @GetMapping("/eog")
    public ResponseEntity<List<EOG>> getAllEogData() {return ResponseEntity.ok(healthDataService.getAllEogData());
    }

   /*@PostMapping("/ecg")
    public ResponseEntity<String> saveECGData(@RequestBody ECG ecg) {
        modelDataService.createECGDataCSV(ecg);
        healthDataService.processAndSaveECGData(ecg);
        return ResponseEntity.ok("ECG data processed and saved successfully.");
    }*/
   @PostMapping("/ecg")
   public ResponseEntity<String> saveECGData(@RequestBody ECG ecg) {
       // 받은 JSON 데이터를 그대로 출력
       System.out.println("Received JSON: " + ecg);

       // 기존 로직 수행
       modelDataService.createECGDataCSV(ecg);
       healthDataService.processAndSaveECGData(ecg);

       return ResponseEntity.ok("ECG data processed and saved successfully.");
   }

    /*@PostMapping("/ecg")
    public ResponseEntity<String> forwardToFastAPI(@RequestBody Map<String, Object> requestBody) {
        // FastAPI URL
        String fastApiUrl = "http://localhost:8082/predict";
        RestTemplate restTemplate = new RestTemplate();

        try {
            // FastAPI로 보낼 데이터에서 device_id 제거
            Map<String, Object> filteredRequestBody = new HashMap<>(requestBody);
            filteredRequestBody.remove("device_id");

            // FastAPI로 데이터 전송
            ResponseEntity<Map> response = restTemplate.postForEntity(fastApiUrl, filteredRequestBody, Map.class);

            // HTTP 상태 코드 출력
            System.out.println("HTTP Status Code: " + response.getStatusCode());

            // JSON 응답 바디 출력
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                System.out.println("Response Body (JSON): " + responseBody);

                // FastAPI 결과 추출
                String result = responseBody.get("result").toString();
                System.out.println("Prediction Result: " + result);

                return ResponseEntity.ok("FastAPI result: " + result);
            } else {
                System.out.println("FastAPI 호출 실패: 상태 코드 " + response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).body("FastAPI call failed");
            }
        } catch (Exception e) {
            // 예외 처리
            System.out.println("FastAPI 호출 중 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FastAPI call error: " + e.getMessage());
        }
    }*/






    @PostMapping("/emg")
    public ResponseEntity<String> saveEMGData(@RequestBody EMG emg) {
        modelDataService.createEMGDataCSV(emg);
        healthDataService.processAndSaveEMGData(emg);
        return ResponseEntity.ok("EMG data processed and saved successfully.");
    }

    @PostMapping("/gsr")
    public ResponseEntity<String> saveGSRData(@RequestBody GSR gsr) {
        modelDataService.createGSRDataCSV(gsr);
        healthDataService.processAndSaveGSRData(gsr);
        return ResponseEntity.ok("GSR data processed and saved successfully.");
    }

}
