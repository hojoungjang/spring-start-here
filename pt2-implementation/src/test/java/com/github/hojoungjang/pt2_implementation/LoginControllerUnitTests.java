package com.github.hojoungjang.pt2_implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.github.hojoungjang.pt2_implementation.controllers.LoginController;
import com.github.hojoungjang.pt2_implementation.processor.LoginProcessor;
import com.github.hojoungjang.pt2_implementation.service.LoggedUserManagementService;
import com.github.hojoungjang.pt2_implementation.service.LoginCountService;

@ExtendWith(MockitoExtension.class)
public class LoginControllerUnitTests {
    @Mock
    private Model model;

    @Mock
    private LoginProcessor loginProcessor;

    @Mock
    private LoggedUserManagementService lumService;

    @Mock
    private LoginCountService loginCountService;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginSucceedsTest() {
        given(loginProcessor.login()).willReturn(true);

        String result = loginController.login("username", "password", model);

        assertEquals(result, "redirect:/main");
    }

    @Test
    public void loginFailsTest() {
        given(loginProcessor.login()).willReturn(false);

        String result = loginController.login("username", "password", model);

        assertEquals(result, "login.html");
        verify(model).addAttribute("message", "Login failed!");
    }
}
