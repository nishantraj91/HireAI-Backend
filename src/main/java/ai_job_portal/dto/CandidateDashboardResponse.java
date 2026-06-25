package ai_job_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateDashboardResponse {

    private Long applicationId;
    private Long jobId;
    private String jobTitle;
    private String company;
    private String status;
}
