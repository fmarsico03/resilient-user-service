package com.resilienttasks.resilient_user_service.service;

import org.springframework.stereotype.Service;
import com.resilienttasks.resilient_user_service.dto.auth.AuthResponse;
import com.resilienttasks.resilient_user_service.dto.auth.LoginRequest;
import com.resilienttasks.resilient_user_service.dto.auth.RegisterRequest;
import com.resilienttasks.resilient_user_service.entity.*;
import com.resilienttasks.resilient_user_service.config.JwtUtils;
import com.resilienttasks.resilient_user_service.validations.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        var user = userService.findByEmail(loginRequest.getEmail());
        Validations.validatePassword(user.getEmail(), loginRequest.getPassword(), user.getPassword(), passwordEncoder);
        String token = jwtUtils.generateToken(user.getId(), user.getRole(), user.getEmail());
        return AuthResponse.builder()
        .token(token)
        .expiresIn(jwtUtils.getEXPIRATION_MS()/1000)
        .userId(user.getId())
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        Validations.validateEmail(userService.findByEmail(registerRequest.getEmail()));
        User user = User.builder().name(registerRequest.getName())
        .id(UUID.randomUUID().toString())
        .lastName(registerRequest.getLastName())
        .email(registerRequest.getEmail())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .role(Rol.BASIC)
        .createdAt(String.valueOf(System.currentTimeMillis()))
        .updatedAt(String.valueOf(System.currentTimeMillis()))
        .build();
        
        User savedUser = userService.createUser(user);
        String token = jwtUtils.generateToken(savedUser.getId(), savedUser.getRole(), savedUser.getEmail());

        return AuthResponse.builder()
        .token(token)
        .expiresIn(jwtUtils.getEXPIRATION_MS()/1000)
        .userId(savedUser.getId())
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
    }
}
