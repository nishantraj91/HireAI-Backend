package ai_job_portal.service;

import ai_job_portal.dto.RecruiterDashboardResponse;
import ai_job_portal.entity.Application;
import ai_job_portal.entity.Job;
import ai_job_portal.entity.User;
import ai_job_portal.repository.ApplicationRepository;
import ai_job_portal.repository.JobRepository;
import ai_job_portal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruiterDashboardService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;


    public List<RecruiterDashboardResponse> getDashboard(
            String email) {

        User recruiter =
                userRepository
                        .findByEmail(email)
                        .orElse(null);

        if (recruiter == null) {
            return new ArrayList<>();
        }


        List<Job> jobs =
                jobRepository.findAll()
                        .stream()
                        .filter(job ->
                                recruiter.getId()
                                        .equals(job.getRecruiterId()))
                        .toList();


        List<RecruiterDashboardResponse> response =
                new ArrayList<>();


        for (Job job : jobs) {

            List<Application> applications =
                    applicationRepository
                            .findAll()
                            .stream()
                            .filter(app ->
                                    app.getJobId()
                                            .equals(job.getId()))
                            .toList();


            List<Long> applicants =
                    applications.stream()
                            .map(Application::getUserId)
                            .toList();


            RecruiterDashboardResponse data =
                    new RecruiterDashboardResponse(

                            job.getId(),

                            job.getTitle(),

                            job.getCompany(),

                            job.getLocation(),

                            applications.size(),

                            applicants
                    );

            response.add(data);
        }

        return response;
    }
}