package ai_job_portal.controller;

import ai_job_portal.dto.LoginRequest;
import ai_job_portal.dto.LoginResponse;
import ai_job_portal.dto.UserDTO;
import ai_job_portal.entity.User;
import ai_job_portal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }
}