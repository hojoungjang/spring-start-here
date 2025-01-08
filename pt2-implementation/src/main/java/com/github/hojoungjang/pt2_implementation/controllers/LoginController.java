package com.github.hojoungjang.pt2_implementation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.hojoungjang.pt2_implementation.processor.LoginProcessor;
import com.github.hojoungjang.pt2_implementation.service.LoggedUserManagementService;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;
    private final LoggedUserManagementService lumService;

    public LoginController(
        LoginProcessor loginProcessor,
        LoggedUserManagementService lumService
    ) {
        this.loginProcessor = loginProcessor;
        this.lumService = lumService;
    }

    @GetMapping("/main")
    public String main(
        @RequestParam(required = false) String logout,
        Model page
    ) {
        if (logout != null) {
            lumService.setUsername(null);
        }

        String sessionUsername = lumService.getUsername();
        if (sessionUsername == null) {
            return "redirect:/";
        }
        page.addAttribute("username", sessionUsername);
        return "main.html";
    }

    @GetMapping("/")
    public String loginGet(Model page) {
        String sessionUsername = lumService.getUsername();
        if (sessionUsername != null) {
            page.addAttribute("username", sessionUsername);
            return "main.html";
        }
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
            page.addAttribute("username", lumService.getUsername());
            return "main.html";
        }
        page.addAttribute("message", "Login failed!");
        return "login.html";
    }
}
