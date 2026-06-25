package ai_job_portal.controller;

import ai_job_portal.dto.AiMatchingResponse;
import ai_job_portal.service.AiMatchingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiMatchingController {


    @Autowired
    private AiMatchingService aiMatchingService;


    @GetMapping("/match/{userId}/{jobId}")
    public AiMatchingResponse calculateMatch(
            @PathVariable Long userId,
            @PathVariable Long jobId) {


        return aiMatchingService.calculateMatchScore(
                userId,
                jobId
        );
    }
}