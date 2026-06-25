package ai_job_portal.service;

import java.util.List;
import ai_job_portal.entity.Application;
import ai_job_portal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application applyForJob(Application application) {
        application.setStatus("APPLIED");
        return applicationRepository.save(application);
    }
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
    public List<Application> getApplicationsByUserId(Long userId) {
        return applicationRepository.findByUserId(userId);
    }
    public Application updateApplicationStatus(Long id, String status) {

        Application application =
                applicationRepository.findById(id).orElse(null);

        if (application != null) {
            application.setStatus(status);
            return applicationRepository.save(application);
        }

        return null;
    }
    public List<Application> getApplicationsByJobId(Long jobId) {

        return applicationRepository.findByJobId(jobId);
    }
}