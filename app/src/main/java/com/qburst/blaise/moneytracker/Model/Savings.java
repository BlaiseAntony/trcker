package com.qburst.blaise.moneytracker.Model;

public class Savings {
    private int id;
    private int amount;

    public Savings(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
