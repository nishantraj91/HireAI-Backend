package ai_job_portal.controller;

import ai_job_portal.entity.Application;
import ai_job_portal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

//    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/apply")
    public Application applyForJob(@RequestBody Application application) {
        return applicationService.applyForJob(application);
    }
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }
    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUserId(
            @PathVariable Long userId) {

        return applicationService.getApplicationsByUserId(userId);
    }

    @GetMapping("/all")
    public List<Application> getAllApplicationsForRecruiter() {

        return applicationService.getAllApplications();

    }

    @PreAuthorize("hasRole('RECRUITER')")
    @PutMapping("/{id}/status")
    public Application updateStatus(
            @PathVariable Long id,
            @RequestBody Application application) {

        return applicationService.updateApplicationStatus(
                id,
                application.getStatus()
        );
    }
    @PreAuthorize("hasRole('RECRUITER')")
    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJobId(
            @PathVariable Long jobId) {

        return applicationService.getApplicationsByJobId(jobId);
    }
}