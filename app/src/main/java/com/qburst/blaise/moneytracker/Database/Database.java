package com.qburst.blaise.moneytracker.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qburst.blaise.moneytracker.Model.Category;
import com.qburst.blaise.moneytracker.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(Context c) {
        super(c, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists category(itemId int primary key, itemName varchar(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertCategory(String categoryName, boolean type) {
        SQLiteDatabase db = getReadableDatabase();
        int id;
        if(type) {
            Cursor cursor = db.query("category", new String[]{"max(itemId) as max"}, null, null, null, null, null);
            cursor.moveToFirst();
            id = cursor.getInt(cursor.getColumnIndex("max")) + 1;
            cursor.close();
        }
        else {
            Cursor cursor = db.query("category", new String[]{"min(itemId) as min"}, null, null, null, null, null);
            cursor.moveToFirst();
            id = cursor.getInt(cursor.getColumnIndex("min")) - 1;
            cursor.close();
        }
        db.execSQL("insert into category values(" + id + ",'" + categoryName + "')");
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from category", null);
        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("itemName"));
                boolean type = cursor.getInt(cursor.getColumnIndex("itemId"))>0;
                Category category = new Category(name,type);
                categories.add(category);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        return transactions;
    }
}
