package ai_job_portal.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterDashboardResponse {

    private Long jobId;

    private String title;

    private String company;

    private String location;

    private int totalApplications;

    private List<Long> applicants;
}