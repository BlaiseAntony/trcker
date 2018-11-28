package com.qburst.blaise.moneytracker.Model;

public class Category {
    private String name;
    private boolean type;
    private int id;

    public Category(String n,int id, boolean t) {
        this.name = n;
        this.type = t;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public boolean isType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
