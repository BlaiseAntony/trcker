package com.qburst.blaise.moneytracker.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.qburst.blaise.moneytracker.Database.Database;
import com.qburst.blaise.moneytracker.Model.Category;
import com.qburst.blaise.moneytracker.Model.Savings;
import com.qburst.blaise.moneytracker.R;

import java.util.ArrayList;
import java.util.List;

public class AddSavings extends AppCompatActivity {
    private Database db;
    private List<Category> categories;
    private Spinner spinner;
    private int itemId;
    private int amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_savings_activity);
        db = new Database(this);
        categories = db.getCategories();
        List<String> strings= new ArrayList<>();
        for(int i=0;i<categories.size();i++) {
            Category c = categories.get(i);
            if(!c.isType()) {
                strings.add(c.getName());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, strings);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemId = categories.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void saveData(View view) {
        EditText e =findViewById(R.id.amount);
        amount = Integer.parseInt(String.valueOf(e.getText()));
        Savings savings = new Savings(itemId,amount);
        db.insertSavings(savings);
        onBackPressed();
    }
}
