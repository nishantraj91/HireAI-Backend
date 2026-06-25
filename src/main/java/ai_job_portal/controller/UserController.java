package ai_job_portal.controller;

import ai_job_portal.dto.UserDTO;
import ai_job_portal.entity.User;
import ai_job_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ai_job_portal.dto.LoginRequest;
import ai_job_portal.dto.LoginResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }
    @PutMapping("/skills/{userId}")
    public User saveSkills(
            @PathVariable Long userId,
            @RequestBody String skills) {

        return userService.saveSkills(
                userId,
                skills
        );
    }
}