package com.resilienttasks.resilient_user_service.repository;

import com.resilienttasks.resilient_user_service.entity.User;
import java.util.List;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
    List<User> findAll();
}
