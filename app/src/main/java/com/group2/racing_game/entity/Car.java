package com.group2.racing_game.entity;

import java.io.Serializable;

public class Car implements Serializable {
    private int id;
    private String name;
    private int icon;
    private double minSpeed;
    private double maxSpeed;
    private double rate;

    public Car(int id, String name, int icon, double minSpeed, double maxSpeed, double rate) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.rate = rate;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
