package com.company.repositories;

import com.company.models.City;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс преобразует данные из файла в список городов, хранит и выдает этот список.
 *
 * @see City
 */
public class CityRepository {
    /** Список городов*/
    private List<City> cities;

    /**
     * Загрузка данных о городах в список cities
     *
     * @param path путь к файлу с данными о городах
     * @throws IOException при неверно указанном пути
     */
    public CityRepository(String path) throws IOException {
            Scanner scanner = new Scanner(Paths.get(path));
            scanner.useDelimiter(System.getProperty("line.separator")); //передаем сканнеру системный разделитель строк

            cities = new ArrayList<>();

            //пока в файле есть строки
            for(int i = 0; scanner.hasNext(); i++){
                cities.add(parseCityCSVLine(scanner.next())); //парсим строку в город и добавляем в список
                System.out.println(cities.get(i).toString()); //выводим очередной город
            }

            scanner.close();
    }

    /**
     * Преобразование строки в город
     *
     * @param line строка с данными
     * @return {@link City}
     */
    private City parseCityCSVLine(String line){
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*"); //пропускаем номер строки

        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = scanner.hasNext() ? scanner.next() : null; //не у всех городов есть это поле

        scanner.close();

        return new City(name,region,district,population,foundation);
    }

    /**
     * Получение списка городов
     *
     * @return {@link List<City>}
     */
    public List<City> getCities() {
        return new ArrayList<>(cities);
    }
}

