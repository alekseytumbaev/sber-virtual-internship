package com.company.services;

import com.company.models.City;
import com.company.repositories.CityRepository;

/**
 * Класс, совершающий какие-либо действия над городами из репозитория.
 *
 * @see CityRepository
 */
public class CityService {

    private final CityRepository cityRepository;

    /**
     * Конструктор принимает репозиторий, с которым будет работать сервис.
     *
     * @param cityRepository
     */
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Вывод в консоль списка городов
     */
    public void printCities() {
        cityRepository.getCities().forEach(System.out::println);
    }

    /**
     * Вывод в консоль списка городов, отсортированного по наименованию
     * в алфавитном порядке по убыванию с учетом регистра
     */
    public void printCitiesSortedByName() {
        cityRepository.getCitiesSortedByName().forEach(System.out::println);
    }

    /**
     * Вывод в консоль списка городов, отсортированного по федеральному округу и наименованию
     *  в алфавитном порядке по убыванию с учетом регистра
     */
    public void printCitiesSortedByDistrictAndName() {
        cityRepository.getCitiesSortedByDistrictAndName().forEach(System.out::println);
    }

    /**
     * Преобразование в массив списка городов, вывод индекса и числа жителей самого густонаселенного города
     */
    public void printMostPopulousCityIndexAndPopulation() {
        int maxInd = 0, maxPopulation = 0;

        City[] citiesArray = cityRepository.getCities().toArray(new City[0]); //получаем список и преобразуем в массив

        //ищем максимальное число жителей и индекс
        for (int i = 0; i < citiesArray.length; i++)
            if (citiesArray[i].getPopulation() > maxPopulation){
                maxPopulation = citiesArray[i].getPopulation();
                maxInd = i;
            }

        System.out.println("[" + maxInd + "] = " + maxPopulation);
    }
}
