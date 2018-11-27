package com.qburst.blaise.moneytracker.Fragment.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qburst.blaise.moneytracker.Model.Category;
import com.qburst.blaise.moneytracker.R;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private List<Category> categories;
    private Context context;

    public CategoryRecyclerViewAdapter(List<Category> categories, Context c) {
        this.categories = categories;
        this.context = c;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Category category = categories.get(i);
        viewHolder.categoryName.setText(category.getName());
        if(category.isType()) {
            viewHolder.categoryName.setTextColor(Color.GREEN);
        }
        else {
            viewHolder.categoryName.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryName;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryName = itemView.findViewById(R.id.name);
        }
    }
}
