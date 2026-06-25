package ai_job_portal.service;

import ai_job_portal.dto.CandidateDashboardResponse;
import ai_job_portal.entity.Application;
import ai_job_portal.entity.Job;
import ai_job_portal.repository.ApplicationRepository;
import ai_job_portal.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateDashboardService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<CandidateDashboardResponse> getDashboard(
            Long userId) {

        List<Application> applications =
                applicationRepository.findByUserId(userId);

        List<CandidateDashboardResponse> response =
                new ArrayList<>();

        for (Application application : applications) {

            Job job =
                    jobRepository.findById(
                                    application.getJobId())
                            .orElse(null);

            if (job != null) {

                response.add(
                        new CandidateDashboardResponse(

                                application.getId(),

                                job.getId(),

                                job.getTitle(),

                                job.getCompany(),

                                application.getStatus()
                        )
                );
            }
        }

        return response;
    }
}