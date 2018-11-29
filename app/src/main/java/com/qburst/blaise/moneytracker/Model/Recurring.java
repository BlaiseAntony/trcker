package com.qburst.blaise.moneytracker.Model;

public class Recurring {

    private int itemId;
    private int amount;
    private int date;
    private int month;
    private int year;
    private int interval;

    public Recurring(int itemId, int year, int month, int date, int amount, int interval) {
        this.itemId = itemId;
        this.amount = amount;
        this.date = date;
        this.month = month;
        this.year = year;
        this.interval = interval;
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getInterval() {
        return interval;
    }
}
