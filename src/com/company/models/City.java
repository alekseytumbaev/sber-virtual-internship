package com.company.models;

/**
 * Модель для хранения информации о городе
 */
public class City {
    /** Наименование города*/
    private String name;
    /** Регион*/
    private String region;
    /** Федеральный округ*/
    private String district;
    /** Население*/
    private int population;
    /** Дата основания или первого упоминания*/
    private String foundation;

    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public String getFoundation() {
        return foundation;
    }
}
