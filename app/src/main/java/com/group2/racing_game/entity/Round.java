package com.group2.racing_game.entity;

public class Round {
    private int id;
    private Car carWin;

    public Round(Car carWin, int id) {
        this.carWin = carWin;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCarWin() {
        return carWin;
    }

    public void setCarWin(Car carWin) {
        this.carWin = carWin;
    }
}
