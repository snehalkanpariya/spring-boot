package com.gvp.unit4.repository;

import com.gvp.unit4.model.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idSequence.getAndIncrement());
        }
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> findByUsername(String username) {
        return users.values().stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }
}