package com.github.hojoungjang.pt2_implementation.processor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.github.hojoungjang.pt2_implementation.service.LoggedUserManagementService;
import com.github.hojoungjang.pt2_implementation.service.LoginCountService;

@Component
@RequestScope
public class LoginProcessor {
    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;
    
    private String username;
    private String password;

    public LoginProcessor(
        LoggedUserManagementService loggedUserManagementService,
        LoginCountService loginCountService
    ) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        loginCountService.increment();
        String sessionUsername = loggedUserManagementService.getUsername();
        String sessionPassword = loggedUserManagementService.getPassword();
        if (sessionUsername == null) {
            loggedUserManagementService.setUsername(username);
            loggedUserManagementService.setPassword(password);
            return true;
        }
        if (username.equals(sessionUsername) && password.equals(sessionPassword)) {
            return true;
        }
        return false;
    }
}
