package com.resilienttasks.resilient_user_service.repository;

import com.resilienttasks.resilient_user_service.entity.User;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
}
