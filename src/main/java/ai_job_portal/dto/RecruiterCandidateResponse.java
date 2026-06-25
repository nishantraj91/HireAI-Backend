package ai_job_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecruiterCandidateResponse {

    private Long applicationId;

    private Long candidateId;

    private String candidateEmail;

    private String status;
}