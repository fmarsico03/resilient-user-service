package com.resilienttasks.resilient_user_service.service;

import java.util.List;
import com.resilienttasks.resilient_user_service.entity.User;
import com.resilienttasks.resilient_user_service.dto.auth.RegisterRequest;
import com.resilienttasks.resilient_user_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import com.resilienttasks.resilient_user_service.exception.NotFoundException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(@Qualifier("InMemoryUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found with email: " + email);
        }
        return user;
    }
}