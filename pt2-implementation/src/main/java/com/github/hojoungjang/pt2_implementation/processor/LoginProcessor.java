package com.github.hojoungjang.pt2_implementation.processor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        if (username.equals("admin") && password.equals("password")) {
            return true;
        }
        return false;
    }
}
