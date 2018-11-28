package com.qburst.blaise.moneytracker.Fragment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qburst.blaise.moneytracker.Database.Database;
import com.qburst.blaise.moneytracker.Model.Category;
import com.qburst.blaise.moneytracker.Model.Savings;
import com.qburst.blaise.moneytracker.R;

import java.util.List;

public class SavingsRecyclerViewAdapter extends RecyclerView.Adapter<SavingsRecyclerViewAdapter.ViewHolder> {
    private List<Savings> savingsList;
    private Context context;

    public SavingsRecyclerViewAdapter(List<Savings> savingsList, Context c) {
        this.savingsList = savingsList;
        this.context = c;
    }

    @NonNull
    @Override
    public SavingsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.savings_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavingsRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Database db = new Database(context);
        Savings savings = savingsList.get(i);
        Category category = db.getCategoryItem(savings.getId());
        viewHolder.item.setText(category.getName());
        viewHolder.amount.setText(String.valueOf(savings.getAmount()));
    }

    @Override
    public int getItemCount() {
        return savingsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item;
        private TextView amount;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
