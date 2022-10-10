package com.spring.lab2.repositories;

import com.spring.lab2.entities.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<Integer, User> map = new HashMap<>();
    private int currentId = 0;

    public User save(User user) {
        if (user.getId() != null && map.containsKey(user.getId())) {
            map.put(user.getId(), user);
            return user;
        }
        user.setId(currentId);
        map.put(user.getId(), user);
        currentId++;
        return user;
    }

    public User findById(int id) {
        return map.get(id);
    }

    public Optional<User> findByUsername(String username) {
            return map.values().stream()
                    .peek(System.out::println)
                    .filter(u -> u.getName().equals(username))
                    .limit(1)
                    .findFirst();
    }
}
