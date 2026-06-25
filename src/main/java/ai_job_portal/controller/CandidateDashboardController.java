package ai_job_portal.controller;

import ai_job_portal.dto.CandidateDashboardResponse;
import ai_job_portal.service.CandidateDashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateDashboardController {

    @Autowired
    private CandidateDashboardService candidateDashboardService;

    @GetMapping("/dashboard/{userId}")
    public List<CandidateDashboardResponse> getDashboard(
            @PathVariable Long userId) {

        return candidateDashboardService
                .getDashboard(userId);
    }
}