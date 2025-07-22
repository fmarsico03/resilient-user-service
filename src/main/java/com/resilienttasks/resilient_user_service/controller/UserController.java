package com.resilienttasks.resilient_user_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.resilienttasks.resilient_user_service.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("User service is alive!");
    }
    @GetMapping
    public List<User> getAllUsers() {
        return null;
    }

    
}