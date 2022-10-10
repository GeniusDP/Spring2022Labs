package com.spring.lab2;

import com.spring.lab2.entities.User;
import com.spring.lab2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab2Application implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("bogdan", "pass"));
	}
}
