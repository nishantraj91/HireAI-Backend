package ai_job_portal.service;

import java.util.List;
import ai_job_portal.entity.Job;
import ai_job_portal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ai_job_portal.entity.User;
import ai_job_portal.repository.UserRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
    public Job updateJob(Long id, Job updatedJob) {

        Job existingJob = jobRepository.findById(id).orElse(null);

        if (existingJob != null) {

            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setCompany(updatedJob.getCompany());
            existingJob.setLocation(updatedJob.getLocation());
            existingJob.setSalary(updatedJob.getSalary());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setSkillsRequired(updatedJob.getSkillsRequired());

            return jobRepository.save(existingJob);
        }

        return null;
    }
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    public List<Job> getJobsByRecruiterId(Long recruiterId) {

        return jobRepository.findByRecruiterId(recruiterId);

    }
    public int calculateMatchScore(
            Long userId,
            Long jobId) {

        User user =
                userRepository.findById(userId)
                        .orElse(null);

        Job job =
                jobRepository.findById(jobId)
                        .orElse(null);

        if(user == null ||
                job == null ||
                user.getSkills() == null) {

            return 0;
        }

        String[] userSkills =
                user.getSkills()
                        .toLowerCase()
                        .split(",");

        String[] jobSkills =
                job.getSkillsRequired()
                        .toLowerCase()
                        .split(",");

        int matched = 0;

        for(String jobSkill : jobSkills) {

            for(String userSkill : userSkills) {

                if(jobSkill.trim()
                        .equals(userSkill.trim())) {

                    matched++;
                    break;
                }
            }
        }

        return (matched * 100)
                / jobSkills.length;
    }
}