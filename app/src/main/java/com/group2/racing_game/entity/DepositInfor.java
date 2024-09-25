package com.group2.racing_game.entity;

import java.time.LocalDateTime;

public class DepositInfor {
    private int userId;
    private double amount;
    private LocalDateTime createAt;

    public DepositInfor(int userId, double amount, LocalDateTime createAt) {
        this.userId = userId;
        this.amount = amount;
        this.createAt = createAt;
    }

    public int getUserId() {
        return userId;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
