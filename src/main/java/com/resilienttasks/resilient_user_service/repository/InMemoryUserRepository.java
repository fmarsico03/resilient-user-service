package com.resilienttasks.resilient_user_service.repository;

import com.resilienttasks.resilient_user_service.entity.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;

@Repository("InMemoryUserRepository")
public class InMemoryUserRepository implements UserRepository {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        users.put(user.getEmail(), user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return users.get(email);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
} 