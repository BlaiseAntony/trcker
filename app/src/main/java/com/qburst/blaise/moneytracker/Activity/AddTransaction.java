package com.qburst.blaise.moneytracker.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;

import com.qburst.blaise.moneytracker.Database.Database;
import com.qburst.blaise.moneytracker.Model.Category;
import com.qburst.blaise.moneytracker.Model.Transaction;
import com.qburst.blaise.moneytracker.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.Spinner;
import android.widget.TextView;

public class AddTransaction extends AppCompatActivity implements View.OnClickListener,OnDateSetListener {
    private int day;
    private int month;
    private int year;
    private TextView inputDate;
    private int amount;
    private int itemId;
    private Database db;
    private List<Category> categories;
    private Spinner spinner;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction_activity);
        inputDate = findViewById(R.id.editText2);
        calendar = Calendar.getInstance(TimeZone.getDefault());
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH)+1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        inputDate.setText(year+"/"+month+"/"+day);
        inputDate.setOnClickListener(this);
        db = new Database(this);
        categories = db.getCategories();
        List<String> strings= new ArrayList<>();
        for(int i=0;i<categories.size();i++) {
            strings.add(categories.get(i).getName());
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year=year;
        this.month=month+1;
        this.day=dayOfMonth;
        inputDate.setText(year+"/"+month+"/"+dayOfMonth);
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void saveData(View view) {
        EditText e =findViewById(R.id.amount);
        amount = Integer.parseInt(String.valueOf(e.getText()));
        Transaction transaction = new Transaction(itemId,year,month,day,amount);
        db.insertTransaction(transaction);
        onBackPressed();
    }
}
