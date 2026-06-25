package ai_job_portal.service;

import ai_job_portal.entity.CandidateProfile;
import ai_job_portal.repository.CandidateProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateProfileService {


    @Autowired
    private CandidateProfileRepository candidateProfileRepository;

    public CandidateProfile saveProfile(
            CandidateProfile profile) {

        // Pehle check karo existing profile hai ya nahi
        CandidateProfile existing = candidateProfileRepository
                .findByUserId(profile.getUserId())
                .orElse(null);

        if (existing != null) {
            // Update karo existing profile
            existing.setPhone(profile.getPhone());
            existing.setCollege(profile.getCollege());
            existing.setSkills(profile.getSkills());
            existing.setExperience(profile.getExperience());
            return candidateProfileRepository.save(existing);
        }

        // Naya profile banao
        return candidateProfileRepository.save(profile);
    }

    public CandidateProfile getProfileByUserId(
            Long userId) {

        return candidateProfileRepository
                .findByUserId(userId)
                .orElse(null);
    }

}
