package com.group2.racing_game.DTO;

import com.group2.racing_game.entity.Car;

public class BetDTO {
    private Car car;
    private double cash;

    public BetDTO(Car car, double cash) {
        this.car = car;
        this.cash = cash;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
