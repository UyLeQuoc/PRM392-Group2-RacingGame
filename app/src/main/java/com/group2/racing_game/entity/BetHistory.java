package com.group2.racing_game.entity;

public class BetHistory {
    private int id;
    private Round round;
    private User user;
    private Car car;
    private double betCash;
    private boolean isWin;

    public BetHistory(int id, Round round, User user, Car car, double betCash, boolean isWin) {
        this.id = id;
        this.round = round;
        this.user = user;
        this.car = car;
        this.betCash = betCash;
        this.isWin = isWin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getBetCash() {
        return betCash;
    }

    public void setBetCash(double betCash) {
        this.betCash = betCash;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}
