package com.company.services;

import com.company.models.City;
import com.company.repositories.CityRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

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
     * Преобразование в массив списка городов, поиск самого густонаселенного города путем перебора,
     * вывод индекса и населения города.
     */
    public void printHighestPopulationBruteForce() {
        int maxInd = 0, maxPopulation = 0;

        City[] citiesArray = cityRepository.getCities().toArray(new City[0]); //получаем список и преобразуем в массив

        //ищем максимальное число жителей и индекс
        for (int i = 0; i < citiesArray.length; i++)
            if (citiesArray[i].getPopulation() > maxPopulation){
                maxPopulation = citiesArray[i].getPopulation();
                maxInd = i;
            }

        System.out.println(MessageFormat.format("[{0}] = {1}", maxInd, maxPopulation));
    }

    /**
     * Преобразование в массив списка городов, сортировка слиянием по населению в порядке возрастания,
     * вывод индекса и населения самого густонаселенного города.
     */
    public void printHighestPopulationMergeSort() {

        City[] citiesArray = cityRepository.getCities().toArray(new City[0]); //получаем список и преобразуем в массив

        mergeSort(citiesArray);

        int ind = citiesArray.length - 1;
        System.out.println(MessageFormat.format("[{0}] = {1}", ind, citiesArray[ind].getPopulation()));
    }

    private void mergeSort(City[] inputArr) {
        int inputLength = inputArr.length;

        if (inputLength < 2) return;

        int mid = inputLength/2;
        City[] left = new City[mid];
        City[] right = new City[inputLength - mid];

        System.arraycopy(inputArr, 0, left, 0, mid);
        System.arraycopy(inputArr, mid, right, 0, inputLength - mid);

        mergeSort(left);
        mergeSort(right);

        merge(inputArr,left,right);
    }

    private void merge(City[] inputArr, City[] left, City[] right){
        int leftLength = left.length;
        int rightLength = right.length;

        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength){
            if (left[i].getPopulation() < right[j].getPopulation()){
                inputArr[k] = left[i];
                i++;
            }
            else {
                inputArr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            inputArr[k] = left[i];
            i++;
            k++;
        }

        while (j < rightLength) {
            inputArr[k] = right[j];
            j++;
            k++;
        }
    }

    /**
     * Преобразование в массив списка городов, быстрая сортировка по населению в порядке возрастания,
     * вывод индекса и населения самого густонаселенного города.
     */
    public void printHighestPopulationQuickSort() {

        City[] citiesArray = cityRepository.getCities().toArray(new City[0]); //получаем список и преобразуем в массив

        quickSort(citiesArray);

        int ind = citiesArray.length - 1;
        System.out.println(MessageFormat.format("[{0}] = {1}", ind, citiesArray[ind].getPopulation()));
    }

    private void quickSort(City[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort (City[] arr, int startInd, int endInd) {
        if (startInd >= endInd) return;

        int pivotInd = new Random().nextInt(endInd - startInd) + startInd;
        int pivot = arr[pivotInd].getPopulation();

        swap(arr, pivotInd, endInd);

        int leftPointer = startInd;
        int rightPointer = endInd;

        while (leftPointer < rightPointer) {
            while (arr[leftPointer].getPopulation() <= pivot &&
                    leftPointer < rightPointer){
                leftPointer++;
            }
            while (arr[rightPointer].getPopulation() >= pivot &&
                    leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(arr, leftPointer,rightPointer);
        }

        swap(arr,leftPointer,endInd);

        quickSort(arr, startInd,leftPointer - 1);
        quickSort(arr,leftPointer + 1, endInd);
    }

    private void swap(City[] arr, int index1, int index2) {
        City temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * Вывод региона и количества городов в нем.
     * Регионы отсортированы в алфавитном порядке по убыванию с учетом регистра.
     */
    public void printNumberOfCitiesInEachRegion() {
        List<City> cities = cityRepository.getCitiesSortedByRegion(); //получаем отсортированный по региону список

        String tempRegion = cities.get(0).getRegion(); //запоминаем первый регион
        int k = 0; //кол-во городов изначально 0

        //Проходимся по списку городов
        for (City c : cities) {
            //если у города такой же регион - увеличиваем счетчик
            if (c.getRegion().equals(tempRegion))
                k++;

            //если регион другой
            else {
                System.out.println(tempRegion + " - " + k); //выводим прошлый регион и кол-во городов в нем
                tempRegion = c.getRegion(); //запоминаем новый регион
                k = 1;
            }
        }
    }
}
