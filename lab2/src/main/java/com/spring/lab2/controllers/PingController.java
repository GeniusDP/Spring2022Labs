package com.spring.lab2.controllers;

import com.spring.lab2.dto.request.UserDto;
import com.spring.lab2.entities.User;
import com.spring.lab2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/ping")
    public String getPong(){
        return "pong";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDto dto){
        System.out.println("REGISTER");
        userRepository.save(new User(dto.getUsername(), dto.getPassword()));
        return "OK";
    }

}
