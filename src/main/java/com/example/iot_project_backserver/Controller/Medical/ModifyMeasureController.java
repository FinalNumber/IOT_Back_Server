package com.example.iot_project_backserver.Controller.Medical;

import com.example.iot_project_backserver.entity.User.required_measurements;
import com.example.iot_project_backserver.service.Medical.LoadMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/modifymeasure")
public class ModifyMeasureController {

    @Autowired
    private LoadMeasureService loadMeasureService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> modifymeasure(@RequestParam("userid") String userid, @RequestParam("airflow") Boolean airflow, @RequestParam("bodytemp") Boolean bodytemp, @RequestParam("nibp") Boolean nibp, @RequestParam("spo2") Boolean spo2, @RequestParam("ecg") Boolean ecg, @RequestParam("emg") Boolean emg, @RequestParam("gsr") Boolean gsr) {

        required_measurements updatedMeasurements = loadMeasureService.updateMeasurements(userid, airflow, bodytemp, nibp, spo2, ecg, emg, gsr);

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
