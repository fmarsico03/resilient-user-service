package com.resilienttasks.resilient_user_service.repository;

import com.resilienttasks.resilient_user_service.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;   

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
