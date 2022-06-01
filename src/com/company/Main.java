package com.company;

import com.company.repositories.CityRepository;
import com.company.services.CityService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            CityRepository cityRepository = new CityRepository("src\\resources\\city_ru.csv");
            CityService cityService = new CityService(cityRepository);

            System.out.println("Cities sorted by name");
            cityService.printCitiesSortedByName();

            System.out.println("\nCities sorted by district and name");
            cityService.printCitiesSortedByDistrictAndName();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
