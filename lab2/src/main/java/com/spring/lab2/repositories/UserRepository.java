package com.spring.lab2.repositories;

import com.spring.lab2.entities.User;
import com.spring.lab2.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends AbstractRepository<User> {


    @Override
    public User findById(int id) {
        if (!map.containsKey(id)) {
            throw new UserNotFoundException();
        }
        return super.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return map.values().stream()
                .filter(u -> u.getName().equals(username))
                .limit(1)
                .findFirst();
    }

}
