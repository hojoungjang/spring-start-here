package com.github.hojoungjang.pt2_implementation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hojoungjang.pt2_implementation.model.Country;

@RestController
public class CountryController {
    @GetMapping("/france")
    public ResponseEntity<Country> france() {
        Country c = Country.of("France", 67);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("continent", "Europe")
                .header("capital", "Paris")
                .header("favorite_food", "cheese and wine")
                .body(c);
    }

    @GetMapping("allCountry")
    public List<Country> allCountry() {
        return List.of(
            Country.of("France", 67),
            Country.of("Spain", 47)
        );
    }
}
