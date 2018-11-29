package com.qburst.blaise.moneytracker.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qburst.blaise.moneytracker.Model.Category;
import com.qburst.blaise.moneytracker.Model.Recurring;
import com.qburst.blaise.moneytracker.Model.Savings;
import com.qburst.blaise.moneytracker.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(Context c) {
        super(c, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists category(itemId integer primary key, itemName varchar(20));");
        db.execSQL("create table if not exists transactions(id integer primary key autoincrement, itemId int, year int, month int, day int, amount int , foreign key(itemId) references category(itemId));");
        db.execSQL("create table if not exists savings(id integer primary key autoincrement, itemId int, amount int, foreign key(itemId) references category(itemId));");
        db.execSQL("create table if not exists recurrings(id integer primary key autoincrement, itemId int, year int, month int, day int, amount int, interval int, foreign key(itemId) references category(itemId));");
    }

    public void insertRecurring(Recurring recurring) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into recurrings(itemId,year,month,day,amount,interval) values("+recurring.getItemId()+","+recurring.getYear()+","+recurring.getMonth()+","+recurring.getDate()+","+recurring.getAmount()+","+recurring.getInterval()+")");
    }

    public List<Recurring> getRecurrings() {
        List<Recurring> recurringList = new ArrayList<>();
        int itemId;
        int date;
        int month;
        int year;
        int amount;
        int interval;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recurrings",null);
        if(cursor.moveToFirst()) {
            do {
                itemId = cursor.getInt(cursor.getColumnIndex("itemId"));
                date = cursor.getInt(cursor.getColumnIndex("day"));
                month = cursor.getInt(cursor.getColumnIndex("month"));
                year = cursor.getInt(cursor.getColumnIndex("year"));
                amount = cursor.getInt(cursor.getColumnIndex("amount"));
                interval = cursor.getInt(cursor.getColumnIndex("interval"));
                Recurring recurring = new Recurring(itemId,year,month,date,amount,interval);
                recurringList.add(recurring);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return recurringList;
    }

    public void insertTransaction(Transaction transaction) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into transactions(itemId,year,month,day,amount) values("+transaction.getItemId()+","+transaction.getYear()+","+transaction.getMonth()+","+transaction.getDate()+","+transaction.getAmount()+")");
    }

    public List<Transaction> getTransactions(int m) {
        List<Transaction> transactionList = new ArrayList<>();
        int itemId;
        int date;
        int month;
        int year;
        int amount;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from transactions where month ="+m,null);
        if(cursor.moveToFirst()) {
            do {
                itemId = cursor.getInt(cursor.getColumnIndex("itemId"));
                date = cursor.getInt(cursor.getColumnIndex("day"));
                month = cursor.getInt(cursor.getColumnIndex("month"));
                year = cursor.getInt(cursor.getColumnIndex("year"));
                amount = cursor.getInt(cursor.getColumnIndex("amount"));
                Transaction transaction = new Transaction(itemId,year,month,date,amount);
                transactionList.add(transaction);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return transactionList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertCategory(String categoryName, boolean type) {
        SQLiteDatabase db = getWritableDatabase();
        int id;
        if(type) {
            Cursor cursor = db.query("category", new String[]{"max(itemId) as max"}, null, null, null, null, null);
            cursor.moveToFirst();
            id = cursor.getInt(cursor.getColumnIndex("max")) + 1;
            if(id<1) {
                id = 1;
            }
            cursor.close();
        }
        else {
            Cursor cursor = db.query("category", new String[]{"min(itemId) as min"}, null, null, null, null, null);
            cursor.moveToFirst();
            id = cursor.getInt(cursor.getColumnIndex("min")) - 1;
            if(id>-1) {
                id = -1;
            }
            cursor.close();
        }
        db.execSQL("insert into category values(" + id + ",'" + categoryName + "')");
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        String name;
        int id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from category", null);
        if(cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex("itemName"));
                id = cursor.getInt(cursor.getColumnIndex("itemId"));
                Category category = new Category(name,id,id>0);
                categories.add(category);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    public Category getCategoryItem(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from category where itemId="+id,null);
        if(cursor.moveToFirst()) {
            return new Category(cursor.getString(cursor.getColumnIndex("itemName")),id,id>0);
        }
        else {
            return null;
        }
    }

    public void insertSavings(Savings savings) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into savings(itemId,amount) values("+savings.getId()+","+savings.getAmount()+")");
    }
    public List<Savings> getSavings() {
        List<Savings> savingsList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        int id;
        int amount;
        Cursor cursor = db.rawQuery("select * from savings",null);
        if(cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("itemId"));
                amount = cursor.getInt(cursor.getColumnIndex("amount"));
                Savings savings = new Savings(id, amount);
                savingsList.add(savings);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return savingsList;
    }
}
