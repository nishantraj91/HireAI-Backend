package ai_job_portal.controller;

import ai_job_portal.entity.CandidateProfile;
import ai_job_portal.service.CandidateProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class CandidateProfileController {

    @Autowired
    private CandidateProfileService candidateProfileService;

    @PostMapping
    public CandidateProfile saveProfile(
            @RequestBody CandidateProfile profile) {

        return candidateProfileService.saveProfile(profile);
    }

    @GetMapping("/{userId}")
    public CandidateProfile getProfile(
            @PathVariable Long userId) {

        return candidateProfileService
                .getProfileByUserId(userId);
    }
}