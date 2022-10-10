package com.spring.lab2.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginOutRegisterController {


    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String viewLogoutPage() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "logout";
    }
}
