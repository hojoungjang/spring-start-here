package com.github.hojoungjang.pt2_implementation.model;

public class Country {
    private String name;
    private int population;

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public static Country of(String name, int population) {
        Country country = new Country();
        country.name = name;
        country.population = population;
        return country;
    }
}
