package com.resilienttasks.resilient_user_service.service;

import java.util.List;
import com.resilienttasks.resilient_user_service.entity.User;
import com.resilienttasks.resilient_user_service.dto.auth.RegisterRequest;
import com.resilienttasks.resilient_user_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(@Qualifier("InMemoryUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");
        user.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        user.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return null;
    }
}