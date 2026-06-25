package ai_job_portal.repository;

import ai_job_portal.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository
        extends JpaRepository<Resume, Long> {

    Resume findTopByUserIdOrderByIdDesc(Long userId);

}