package com.qburst.blaise.moneytracker.Model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Transaction {

    private int itemId;
    private int amount;
    private int date;
    private int month;
    private int year;

    public Transaction(int itemId, int year, int month, int date, int amount) {
        this.itemId = itemId;
        this.amount = amount;
        this.date = date;
        this.month = month;
        this.year = year;
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
}
