package ai_job_portal.service;

import ai_job_portal.dto.RecruiterCandidateResponse;
import ai_job_portal.entity.Application;
import ai_job_portal.entity.User;
import ai_job_portal.repository.ApplicationRepository;
import ai_job_portal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruiterCandidateService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RecruiterCandidateResponse> getCandidates(
            Long jobId) {

        List<Application> applications =
                applicationRepository.findByJobId(jobId);

        List<RecruiterCandidateResponse> response =
                new ArrayList<>();

        for (Application application : applications) {

            User candidate =
                    userRepository.findById(
                                    application.getUserId())
                            .orElse(null);

            if (candidate != null) {

                response.add(
                        new RecruiterCandidateResponse(

                                application.getId(),

                                candidate.getId(),

                                candidate.getEmail(),

                                application.getStatus()
                        )
                );
            }
        }

        return response;
    }
}
