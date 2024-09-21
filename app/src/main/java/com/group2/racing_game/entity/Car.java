package com.group2.racing_game.entity;

public class Car {
    private int id;
    private String name;
    private String icon;
    private double minSpeed;
    private double maxSpeed;
    private double rating;

    public Car(int id, String name, String icon, double minSpeed, double maxSpeed, double rating) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
