package com.qburst.blaise.moneytracker.Fragment.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qburst.blaise.moneytracker.Database.Database;
import com.qburst.blaise.moneytracker.Model.Category;
import com.qburst.blaise.moneytracker.Model.Transaction;
import com.qburst.blaise.moneytracker.R;

import java.util.List;

public class TransactionRecyclerViewAdapter extends RecyclerView.Adapter<TransactionRecyclerViewAdapter.ViewHolder> {
    private List<Transaction> transactions;
    private Context context;

    public TransactionRecyclerViewAdapter(List<Transaction> transactions, Context c) {
        this.transactions = transactions;
        this.context = c;
    }

    @NonNull
    @Override
    public TransactionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.transaction_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Database db = new Database(context);
        Transaction transaction = transactions.get(i);
        Category category = db.getCategoryItem(transaction.getItemId());
        viewHolder.item.setText(category.getName());
        viewHolder.amount.setText(String.valueOf(transaction.getAmount()));
        if(category.isType()) {
            viewHolder.amount.setTextColor(Color.GREEN);
            viewHolder.item.setTextColor(Color.GREEN);
        }
        else  {
            viewHolder.amount.setTextColor(Color.RED);
            viewHolder.item.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item;
        private TextView amount;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
