package com.example.iot_project_backserver.Controller.Volunteer;

import com.example.iot_project_backserver.service.Volunteer.DesiredService;
import com.example.iot_project_backserver.service.Volunteer.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/volunteercalldelete")
public class DesiredDeleteController {

    private final DesiredService desiredService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> callvolunteermodify(@RequestParam("userid") String userid,
                                                                   @RequestParam("desireddate") String desireddate) {

        desiredService.deleteDesiredVolunteerDateByUseridAndDate(userid, desireddate);

        return null;
    }
}
