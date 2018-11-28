package com.qburst.blaise.moneytracker.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qburst.blaise.moneytracker.Database.Database;
import com.qburst.blaise.moneytracker.Model.Savings;
import com.qburst.blaise.moneytracker.Model.Transaction;
import com.qburst.blaise.moneytracker.R;

import java.util.ArrayList;
import java.util.List;

import static com.qburst.blaise.moneytracker.Activity.MainActivity.BALANCES;
import static com.qburst.blaise.moneytracker.Activity.MainActivity.currentMonth;
import static com.qburst.blaise.moneytracker.Activity.MainActivity.fragment_ID;

public class BalanceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.balance_fragment,container,false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragment_ID = BALANCES;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int totalIncome = 0;
        int totalSpent = 0;
        int totalSaved = 0;
        int availableBalance;
        int spentSaved = 0;
        List<Savings> balanceInSavings = new ArrayList<>();
        Context c = getContext();
        Database db = new Database(c);
        List<Transaction> transactionList = db.getTransactions(currentMonth);
        List<Savings> savingsList = db.getSavings();
        Transaction transaction;
        for(int i=0;i<transactionList.size();i++) {
            transaction = transactionList.get(i);
            if(transaction.getItemId()>0) {
                totalIncome += transaction.getAmount();
            }
            else {
                totalSpent += transaction.getAmount();
            }
        }
        Savings savings;
        for(int i=0;i<savingsList.size();i++) {
            savings = savingsList.get(i);
            totalSaved += savings.getAmount();
            int id = savings.getId();
            int spent = 0;
            for(int j=0;j<transactionList.size();j++) {
                transaction=transactionList.get(j);
                if(transaction.getItemId() == id) {
                    spent += transaction.getAmount();
                }
            }
            balanceInSavings.add(new Savings(id,spent));
            spentSaved += spent;
        }
        availableBalance = totalIncome - totalSaved + spentSaved -totalSpent;
        TextView income = view.findViewById(R.id.total_income);
        TextView spent = view.findViewById(R.id.total_spent);
        income.setText(String.valueOf(totalIncome));
        spent.setText(String.valueOf(totalSpent));
        TextView remaining = view.findViewById(R.id.remaining);
        remaining.setText(String.valueOf(totalIncome - totalSpent));
        TextView saved = view.findViewById(R.id.total_saved);
        saved.setText(String.valueOf(totalSaved));
        TextView available =view.findViewById(R.id.total_available);
        available.setText(String.valueOf(availableBalance));
    }

}
