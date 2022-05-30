package com.company;

import com.company.models.City;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //создаем scanner и передаем ему в конструктор путь файла с городами
        try(Scanner scanner = new Scanner(Paths.get(
                "src\\com\\company\\data\\city_ru.csv"))) {

            List<City> cities = new ArrayList<>();

            //передаем сканнеру системный разделитель строк
            scanner.useDelimiter(System.getProperty("line.separator"));

            //пока в файле есть строки
            for(int i = 0; scanner.hasNext(); i++){
                cities.add(parseCityCSVLine(scanner.next())); //парсим строку в город и добавляем в список
                System.out.println(cities.get(i).toString()); //выводим очередной город
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод преобразования строк в город
    private static City parseCityCSVLine(String line){
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.next(); //пропускаем номер города в файле

        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = scanner.hasNext() ? scanner.next() : null; //не для всех городов известен год основания

        return new City(name,region,district,population,foundation);
    }
}
