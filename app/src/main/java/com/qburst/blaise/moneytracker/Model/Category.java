package com.qburst.blaise.moneytracker.Model;

public class Category {
    private String name;
    private boolean type;

    public Category(String n, boolean t) {
        this.name = n;
        this.type = t;
    }

    public String getName() {
        return name;
    }

    public boolean isType() {
        return type;
    }
}
