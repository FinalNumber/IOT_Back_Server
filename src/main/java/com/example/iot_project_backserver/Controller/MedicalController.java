package com.example.iot_project_backserver.Controller;

import com.example.iot_project_backserver.Entity.Medical.patient_assignment;
import com.example.iot_project_backserver.Entity.User.required_measurements;
//import com.example.iot_project_backserver.service.Medical.LoadMeasureService;
import com.example.iot_project_backserver.Service.MedicalService;
//import com.example.iot_project_backserver.service.Medical.PatientAssignmentService;
import com.example.iot_project_backserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MedicalController {

    @Autowired
    private UserService userService;
    @Autowired
    private MedicalService medicalService;

    @PostMapping("/searchpatient")//의료진이 환자를 추가하기 위해 검색
    public ResponseEntity<Map<String, Object>> searchpatient(@RequestParam("searchdata") String searchdata) {
        Map<String, Object> response = new HashMap<>();

        if(searchdata.contains("@")){
            // searchdata를 userid로 간주하고 조회
            Optional<Map<String, String>> userInfo = userService.getUserInfoByUserid(searchdata);
            if (userInfo.isPresent()) {
                Map<String, String> userData = userInfo.get(); // Optional 안의 Map을 꺼냄
                response.put("status", "success");
                response.put("userid", userData.get("userid"));
                response.put("name", userData.get("name"));
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "DataEmpty");
                response.put("userid", null);
                response.put("name", null);
                return ResponseEntity.ok(response);
            }
        }else {
            List<Map<String, String>> userInfoList = userService.getUserInfoByName(searchdata);

            if (!userInfoList.isEmpty()) {
                response.put("status", "success");

                // 여러 개의 유저 정보를 리스트로 담아 response에 추가
                List<Map<String, String>> userData = new ArrayList<>();
                for (Map<String, String> userInfo : userInfoList) {
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("userid", userInfo.get("userid"));
                    userMap.put("name", userInfo.get("name"));
                    userData.add(userMap);
                }

                response.put("data", userData);
            } else {
                response.put("status", "DataEmpty");
                response.put("data", null);
            }

            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/assignmentpatient")//의료진이 담당환자 추가
    public ResponseEntity<Map<String, Object>> assignmentpatient(@RequestParam("medicalid") String medicalid, @RequestParam("userid") String userid) {
        Map<String, Object> response = new HashMap<>();

        // 서비스에서 레코드 존재 여부 확인
        boolean exists = medicalService.checkAssignmentExists(medicalid, userid);
        if (exists) {
            // 이미 존재하는 경우
            response.put("status", "duplication");
        } else {
            // 존재하지 않는 경우 서비스에서 새로운 레코드 저장
            medicalService.saveAssignment(medicalid, userid);
            response.put("status", "success");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/loadpatient")//의료진이 담당 환자 list 받기
    public ResponseEntity<Map<String, Object>> loadpatient(@RequestParam("medicalid") String medicalid) {
        List<patient_assignment> assignments = medicalService.findAssignmentsByMedicalid(medicalid);

        Map<String, Object> response = new HashMap<>();
        if (!assignments.isEmpty()) {
            response.put("status", "success");

            response.put("data", assignments.stream().map(assignment -> {
                Map<String, Object> assignmentData = new HashMap<>();
                assignmentData.put("medicalid", assignment.getMedicalid());
                assignmentData.put("userid", assignment.getUserid());

                // app_user 정보 포함
                if (assignment.getApp_user() != null) {
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("userid", assignment.getApp_user().getUserid());
                    userData.put("name", assignment.getApp_user().getName());
                    userData.put("birth", assignment.getApp_user().getBirth());

                    assignmentData.put("app_user", userData);
                } else {
                    assignmentData.put("app_user", null);
                }

                return assignmentData;
            }).collect(Collectors.toList()));
        } else {
            response.put("status", "DataEmpty");
            response.put("data", null);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/deletepatient")//의료진이 담당 환자 list 삭제
    public ResponseEntity<Map<String, Object>> deletepatient(@RequestParam("medicalid") String medicalid, @RequestParam("userid") String userid) {
        Map<String, Object> response = new HashMap<>();
        boolean isDeleted = medicalService.deletePatientAssignment(medicalid, userid);
        response.put("success", isDeleted);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/loadmeasure")//담당 환자의 필요 측정 요소 list를 불러오기
    public ResponseEntity<Map<String, Object>> loadmeasure(@RequestParam("userid") String userid) {
        required_measurements result = medicalService.checkAndInsert(userid);

        Map<String, Object> response = new HashMap<>();
        response.put("userid", result.getUserid());
        response.put("airflow", result.getAirflow());
        response.put("bodytemp", result.getBodytemp());
        response.put("nibp", result.getNibp());
        response.put("spo2", result.getSpo2());
        response.put("ecg", result.getEcg());
        response.put("emg", result.getEmg());
        response.put("gsr", result.getGsr());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/modifymeasure")//담당 환자의 필요 측정 요소 list를 수정하기
    public ResponseEntity<Map<String, Object>> modifymeasure(@RequestParam("userid") String userid, @RequestParam("airflow") Boolean airflow, @RequestParam("bodytemp") Boolean bodytemp, @RequestParam("nibp") Boolean nibp, @RequestParam("spo2") Boolean spo2, @RequestParam("ecg") Boolean ecg, @RequestParam("emg") Boolean emg, @RequestParam("gsr") Boolean gsr) {

        required_measurements updatedMeasurements = medicalService.updateMeasurements(userid, airflow, bodytemp, nibp, spo2, ecg, emg, gsr);

        Map<String, Object> response = new HashMap<>();
        if (updatedMeasurements != null) {
            response.put("status", "success");
            response.put("updatedMeasurements", updatedMeasurements);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "failure");
            response.put("message", "Record not found for the provided userid");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
