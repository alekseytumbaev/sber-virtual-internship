package com.company;

import com.company.repositories.CityRepository;
import com.company.services.CityService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            CityRepository cityRepository = new CityRepository("src\\resources\\city_ru.csv");

            CityService cityService = new CityService(cityRepository);
            cityService.printCities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
