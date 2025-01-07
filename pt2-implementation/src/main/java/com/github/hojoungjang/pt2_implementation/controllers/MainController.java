package com.github.hojoungjang.pt2_implementation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * To make request parameters optional pass `required` option
 * to @RequestParam annotaions.
 */

@Controller
public class MainController {
    @RequestMapping("/home")
    public String home(
        @RequestParam(required=false, defaultValue="World") String username,
        @RequestParam(required=false, defaultValue="green") String color,
        Model page
    ) {
        page.addAttribute("username", username);
        page.addAttribute("color", color);
        return "home_template.html";
    }

    @RequestMapping("/home2/{username}/{color}")
    public String home2(
        @PathVariable String username,
        @PathVariable String color,
        Model page
    ) {
        page.addAttribute("username", username);
        page.addAttribute("color", color);
        return "home_template.html";
    }
}
