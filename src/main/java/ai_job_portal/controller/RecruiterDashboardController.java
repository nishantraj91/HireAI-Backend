package ai_job_portal.controller;

import ai_job_portal.dto.RecruiterDashboardResponse;
import ai_job_portal.service.RecruiterDashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/recruiter")
public class RecruiterDashboardController {


    @Autowired
    private RecruiterDashboardService recruiterDashboardService;



    @GetMapping("/dashboard")
    public List<RecruiterDashboardResponse> getDashboard(
            Authentication authentication) {


        String email =
                authentication.getName();


        return recruiterDashboardService
                .getDashboard(email);
    }
}