package com.github.hojoungjang.pt2_implementation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.hojoungjang.pt2_implementation.processor.LoginProcessor;
import com.github.hojoungjang.pt2_implementation.service.LoggedUserManagementService;
import com.github.hojoungjang.pt2_implementation.service.LoginCountService;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;
    private final LoggedUserManagementService lumService;
    private final LoginCountService loginCountService;

    public LoginController(
        LoginProcessor loginProcessor,
        LoggedUserManagementService lumService,
        LoginCountService loginCountService
    ) {
        this.loginProcessor = loginProcessor;
        this.lumService = lumService;
        this.loginCountService = loginCountService;
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
        page.addAttribute("loginCount", loginCountService.getCount());
        page.addAttribute("username", sessionUsername);
        return "main.html";
    }

    @GetMapping("/")
    public String loginGet(Model page) {
        String sessionUsername = lumService.getUsername();
        if (sessionUsername != null) {
            return "redirect:/main";
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
            return "redirect:/main";
        }
        page.addAttribute("message", "Login failed!");
        return "login.html";
    }
}
