package ai_job_portal.controller;

import ai_job_portal.dto.RecruiterCandidateResponse;
import ai_job_portal.service.RecruiterCandidateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterCandidateController {

    @Autowired
    private RecruiterCandidateService recruiterCandidateService;

    @GetMapping("/candidates/{jobId}")
    public List<RecruiterCandidateResponse> getCandidates(
            @PathVariable Long jobId) {

        return recruiterCandidateService
                .getCandidates(jobId);
    }
}