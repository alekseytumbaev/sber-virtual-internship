package com.company.services;

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
}
