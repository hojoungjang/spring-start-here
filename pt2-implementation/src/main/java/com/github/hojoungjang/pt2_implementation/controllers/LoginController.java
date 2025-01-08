package com.github.hojoungjang.pt2_implementation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.hojoungjang.pt2_implementation.processor.LoginProcessor;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;

    public LoginController(LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
    }

    @GetMapping("/")
    public String loginGet(Model page) {
        return "login.html";
    }
    
    @PostMapping("/")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
        Model page
    ) {
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        boolean loggedIn = loginProcessor.login();

        if (loggedIn) {
            page.addAttribute("message", "You are logged in!");
        } else {
            page.addAttribute("message", "Login failed!");
        }

        return "login.html";
    }
}
