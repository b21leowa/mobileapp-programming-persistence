package com.example.persistence;

public class Car {
    private long id;
    private String carBrand;
    private String carModel;
    private int topspeed;

    Car(long id, String carBrand, String carModel, int topspeed) {
        this.id = id;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.topspeed = topspeed;
    }

    public long getId() {
        return id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getTopspeedString() {
        return Integer.toString(topspeed);
    }

    public int getTopspeed() {
        return topspeed;
    }
}
