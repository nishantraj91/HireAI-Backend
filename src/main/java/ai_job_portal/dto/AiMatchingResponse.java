package ai_job_portal.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AiMatchingResponse {

    private String candidate;

    private String jobTitle;

    private String company;

    private int matchScore;

    private List<String> matchedSkills;
}