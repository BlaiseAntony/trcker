package com.qburst.blaise.moneytracker.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.qburst.blaise.moneytracker.Database.Database;
import com.qburst.blaise.moneytracker.R;

public class AddCategory extends AppCompatActivity {
    private EditText name;
    private Database db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category_activity);
        name = findViewById(R.id.editText);
        db = new Database(this);
    }

    public void addIncome(View view) {
        db.insertCategory(name.getText().toString(),true);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void addExpence(View view) {
        db.insertCategory(name.getText().toString(),false);
        onBackPressed();
    }
}
