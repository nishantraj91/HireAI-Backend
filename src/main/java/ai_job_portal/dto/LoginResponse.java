package ai_job_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String message;
    private Long userId;
    private String email;
    private String fullName;
    private String role;
    private String token;
}