package ai_job_portal.service;

import ai_job_portal.dto.AiMatchingResponse;
import ai_job_portal.entity.CandidateProfile;
import ai_job_portal.entity.Job;
import ai_job_portal.entity.User;
import ai_job_portal.repository.CandidateProfileRepository;
import ai_job_portal.repository.JobRepository;
import ai_job_portal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AiMatchingService {

    @Autowired
    private CandidateProfileRepository candidateProfileRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;


    public AiMatchingResponse calculateMatchScore(
            Long userId,
            Long jobId) {


        CandidateProfile profile =
                candidateProfileRepository
                        .findByUserId(userId)
                        .orElse(null);


        Job job =
                jobRepository
                        .findById(jobId)
                        .orElse(null);


        User user =
                userRepository
                        .findById(userId)
                        .orElse(null);


        if(profile == null || job == null || user == null) {

            return null;
        }


        List<String> candidateSkills =
                Arrays.stream(profile.getSkills().split(","))
                        .map(String::trim)
                        .map(String::toLowerCase)
                        .toList();


        List<String> requiredSkills =
                Arrays.stream(job.getSkillsRequired().split(","))
                        .map(String::trim)
                        .map(String::toLowerCase)
                        .toList();



        List<String> matchedSkills =
                requiredSkills.stream()
                        .filter(candidateSkills::contains)
                        .toList();



        int score =
                (int)
                        ((matchedSkills.size() * 100)
                                / requiredSkills.size());



        return new AiMatchingResponse(

                user.getFullName(),

                job.getTitle(),

                job.getCompany(),

                score,

                matchedSkills
        );
    }
}