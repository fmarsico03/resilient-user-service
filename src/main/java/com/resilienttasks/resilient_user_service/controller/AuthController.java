package com.resilienttasks.resilient_user_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.resilienttasks.resilient_user_service.dto.auth.AuthResponse;
import com.resilienttasks.resilient_user_service.dto.auth.LoginRequest;
import com.resilienttasks.resilient_user_service.dto.auth.RegisterRequest;
import com.resilienttasks.resilient_user_service.service.UserService;
import com.resilienttasks.resilient_user_service.config.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("User service is alive!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(AuthResponse.builder().token("User service is alive!").build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        var user = userService.registerUser(registerRequest);
        String token = jwtUtils.generateToken(user.getEmail());
        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }
}