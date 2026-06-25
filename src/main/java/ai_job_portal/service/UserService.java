package ai_job_portal.service;

import ai_job_portal.dto.UserDTO;
import ai_job_portal.entity.User;
import ai_job_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ai_job_portal.dto.LoginRequest;
import ai_job_portal.dto.LoginResponse;
import java.util.Optional;
import ai_job_portal.security.JwtUtil;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public UserDTO registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User saved = userRepository.save(user);

        return new UserDTO(
                saved.getId(),
                saved.getFullName(),
                saved.getEmail(),
                saved.getRole()
        );
    }
    public LoginResponse loginUser(LoginRequest request) {

        Optional<User> userOptional =
                userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(
                "Login Successful",
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                token
        );
    }
    public User saveSkills(Long userId, String skills) {

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException("User Not Found"));

        user.setSkills(skills);

        return userRepository.save(user);
    }
}