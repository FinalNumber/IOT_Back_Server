package com.example.iot_project_backserver.Controller.Medical;

import com.example.iot_project_backserver.entity.User.required_measurements;
import com.example.iot_project_backserver.service.Medical.LoadMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loadmeasure")
public class LoadMeasureController {

    @Autowired
    private LoadMeasureService loadMeasureService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> loadmeasure(@RequestParam("userid") String userid) {
        required_measurements result = loadMeasureService.checkAndInsert(userid);

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
}
