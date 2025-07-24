package com.resilienttasks.resilient_user_service.service;

import java.util.List;
import com.resilienttasks.resilient_user_service.entity.User;
import com.resilienttasks.resilient_user_service.dto.auth.RegisterRequest;
import com.resilienttasks.resilient_user_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import com.resilienttasks.resilient_user_service.validations.Validations;
import com.resilienttasks.resilient_user_service.dto.user.UserUpdateRequest;

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

    public User findById(Long id) {
        Validations.notNull(id, "Id");
        User user = userRepository.findById(id);
        Validations.notFoundUser(user, "Id: " + id.toString());
        return user;
    }

    public void deleteUser(Long id) {
        Validations.notNull(id, "Id");
        User user = userRepository.findById(id);
        Validations.notFoundUser(user, "Id: " + id.toString());
        userRepository.delete(user);
    }

    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        Validations.notNull(id, "Id");
        User existingUser = userRepository.findById(id);
        Validations.notFoundUser(existingUser, id.toString());
        if (userUpdateRequest.getName() != null) {
            existingUser.setName(userUpdateRequest.getName());
        }
        if (userUpdateRequest.getLastName() != null) {
            existingUser.setLastName(userUpdateRequest.getLastName());
        }
        userRepository.update(existingUser);
        return existingUser;
    }

    public User findByEmail(String email) {
        Validations.notNull(email, "Email");
        User user = userRepository.findByEmail(email);
        return user;
    }
}