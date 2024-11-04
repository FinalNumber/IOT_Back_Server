package com.example.iot_project_backserver.service.Volunteer;

import com.example.iot_project_backserver.entity.Volunteer.volunteer_assignment;
import com.example.iot_project_backserver.repository.Volunteer.VolunteerAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerAssignmentServiceImpl implements VolunteerAssignmentService {

    private final VolunteerAssignmentRepository volunteerAssignmentRepository;

    @Autowired
    public VolunteerAssignmentServiceImpl(VolunteerAssignmentRepository volunteerAssignmentRepository) {
        this.volunteerAssignmentRepository = volunteerAssignmentRepository;
    }

    @Override
    public volunteer_assignment saveVolunteerAssignment(String volunteerid, String userid, String assignment_date, String text) {
        volunteer_assignment volunteerAssignment = volunteer_assignment.builder()
                .volunteerid(volunteerid)
                .userid(userid)
                .assignment_date(assignment_date)
                .text(text)
                .build();

        return volunteerAssignmentRepository.save(volunteerAssignment);
    }

    @Override
    public List<volunteer_assignment> getVolunteerAssignmentsByVolunteerid(String volunteerid) {
        return volunteerAssignmentRepository.findByVolunteerid(volunteerid);
    }
}
