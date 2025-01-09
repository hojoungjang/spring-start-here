package com.github.hojoungjang.pt2_implementation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("anyoung")
    public String anyoung() {
        return "안녕";
    }
}
