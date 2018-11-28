package com.qburst.blaise.moneytracker.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qburst.blaise.moneytracker.Database.Database;
import com.qburst.blaise.moneytracker.Fragment.Adapter.SavingsRecyclerViewAdapter;
import com.qburst.blaise.moneytracker.Model.Savings;
import com.qburst.blaise.moneytracker.R;

import java.util.List;

import static com.qburst.blaise.moneytracker.Activity.MainActivity.SAVINGS;
import static com.qburst.blaise.moneytracker.Activity.MainActivity.fragment_ID;

public class SavingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.savings_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragment_ID = SAVINGS;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Context c = getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        Database db = new Database(c);
        List<Savings> savingsList = db.getSavings();
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        SavingsRecyclerViewAdapter adapter = new SavingsRecyclerViewAdapter(savingsList,c);
        recyclerView.setAdapter(adapter);
    }
}
