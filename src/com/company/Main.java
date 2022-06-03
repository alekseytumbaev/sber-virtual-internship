package com.company;

import com.company.repositories.CityRepository;
import com.company.services.CityService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            CityRepository cityRepository = new CityRepository("src\\resources\\city_ru.csv");
            CityService cityService = new CityService(cityRepository);

            System.out.println("Most populous city index and population found by brute force");
            cityService.printHighestPopulationBruteForce();

            System.out.println("Most populous city index and population found by merge sort");
            cityService.printHighestPopulationMergeSort();

            System.out.println("Most populous city index and population found by quick sort");
            cityService.printHighestPopulationQuickSort();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
