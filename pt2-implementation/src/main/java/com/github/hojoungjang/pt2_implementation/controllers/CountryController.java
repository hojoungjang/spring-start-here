package com.github.hojoungjang.pt2_implementation.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hojoungjang.pt2_implementation.model.Country;

@RestController
public class CountryController {
    @GetMapping("/france")
    public Country france() {
        return Country.of("France", 67);
    }

    @GetMapping("allCountry")
    public List<Country> allCountry() {
        return List.of(
            Country.of("France", 67),
            Country.of("Spain", 47)
        );
    }
}
