package com.resilienttasks.resilient_user_service.service;

import java.util.List;
import com.resilienttasks.resilient_user_service.entity.*;
import com.resilienttasks.resilient_user_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import com.resilienttasks.resilient_user_service.validations.Validations;
import com.resilienttasks.resilient_user_service.dto.user.UserUpdateRequest;
import org.springframework.security.core.Authentication;

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

    public User findById(Long id, Authentication authentication) {
        Validations.notNull(id, "Id");
        User user = userRepository.findById(id);
        Validations.notFoundUser(user, "Id: " + id.toString());
        Validations.authorizeSelfOrAdmin(this.getCurrentUser(authentication), user);
        return user;
    }

    public void deleteUser(Long id, Authentication authentication) {
        Validations.notNull(id, "Id");
        User user = userRepository.findById(id);
        Validations.notFoundUser(user, "Id: " + id.toString());
        Validations.authorizeSelfOrAdmin(this.getCurrentUser(authentication), user);
        userRepository.delete(user);
    }

    public User updateUser(Long id, UserUpdateRequest userUpdateRequest, Authentication authentication) {
        Validations.notNull(id, "Id");
        User existingUser = userRepository.findById(id);
        Validations.notFoundUser(existingUser, id.toString());
        User currentUser = this.getCurrentUser(authentication);
        Validations.authorizeSelfOrAdmin(currentUser, existingUser);
        if (userUpdateRequest.getName() != null) {
            existingUser.setName(userUpdateRequest.getName());
        }
        if (userUpdateRequest.getLastName() != null) {
            existingUser.setLastName(userUpdateRequest.getLastName());
        }
        if (userUpdateRequest.getEmail() != null) {
            existingUser.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getPassword() != null) {
            existingUser.setPassword(userUpdateRequest.getPassword());
        }
        if (userUpdateRequest.getRole() != null && currentUser.getRole().equals(Rol.ADMIN)) {
            existingUser.setRole(userUpdateRequest.getRole());
        }
        userRepository.update(existingUser);
        return existingUser;
    }

    public User findByEmail(String email) {
        Validations.notNull(email, "Email");
        User user = userRepository.findByEmail(email);
        return user;
    }

    private User getCurrentUser(Authentication authentication) {
        User currentUser = this.findByEmail(authentication.getName());
        Validations.notFoundUser(currentUser, "Current user with email: " + authentication.getName());
        return currentUser;
    }
}