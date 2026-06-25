package ai_job_portal.controller;

import java.util.List;

import ai_job_portal.entity.Job;
import ai_job_portal.entity.User;
import ai_job_portal.repository.UserRepository;
import ai_job_portal.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/jobs")
public class JobController {


    @Autowired
    private JobService jobService;


    @Autowired
    private UserRepository userRepository;



//    @PreAuthorize("hasRole('RECRUITER')")
    @PostMapping
    public Job createJob(
            @RequestBody Job job,
            Authentication authentication) {


        String email =
                authentication.getName();


        User recruiter =
                userRepository
                        .findByEmail(email)
                        .orElse(null);


        if(recruiter != null) {

            job.setRecruiterId(
                    recruiter.getId()
            );
        }


        return jobService.createJob(job);
    }



    @GetMapping
    public List<Job> getAllJobs() {

        return jobService.getAllJobs();
    }

    @GetMapping("/recruiter/{recruiterId}")
    public List<Job> getJobsByRecruiterId(
            @PathVariable Long recruiterId) {

        return jobService.getJobsByRecruiterId(recruiterId);
    }



    @GetMapping("/{id}")
    public Job getJobById(
            @PathVariable Long id) {

        return jobService.getJobById(id);
    }



    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @RequestBody Job job) {

        return jobService.updateJob(id, job);
    }



    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id) {

        jobService.deleteJob(id);

        return "Job deleted successfully";
    }
    @GetMapping("/match/{jobId}/{userId}")
    public int getMatchScore(
            @PathVariable Long jobId,
            @PathVariable Long userId) {

        return jobService
                .calculateMatchScore(
                        userId,
                        jobId
                );
    }
}