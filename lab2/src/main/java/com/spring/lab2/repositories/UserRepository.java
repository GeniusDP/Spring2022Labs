package com.spring.lab2.repositories;

import com.spring.lab2.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends AbstractRepository<User>{

    public Optional<User> findByUsername(String username) {
            return map.values().stream()
                    .peek(System.out::println)
                    .filter(u -> u.getName().equals(username))
                    .limit(1)
                    .findFirst();
    }

}
