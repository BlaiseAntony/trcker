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
import com.qburst.blaise.moneytracker.Model.Recurring;
import com.qburst.blaise.moneytracker.R;

import java.util.List;

public class RecuringRecyclerViewAdapter extends RecyclerView.Adapter<RecuringRecyclerViewAdapter.ViewHolder> {
    private List<Recurring> recurringList;
    private Context context;

    public RecuringRecyclerViewAdapter(List<Recurring> recurringList, Context c) {
        this.recurringList = recurringList;
        this.context = c;
    }

    @NonNull
    @Override
    public RecuringRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recurring_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Database db = new Database(context);
        Recurring recurring = recurringList.get(i);
        Category category = db.getCategoryItem(recurring.getItemId());
        viewHolder.item.setText(category.getName());
        viewHolder.amount.setText(String.valueOf(recurring.getAmount()));
    }

    @Override
    public int getItemCount() {
        return recurringList.size();
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
