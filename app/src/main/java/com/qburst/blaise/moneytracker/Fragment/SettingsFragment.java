package com.qburst.blaise.moneytracker.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qburst.blaise.moneytracker.R;

import static com.qburst.blaise.moneytracker.Activity.MainActivity.SETTINGS;
import static com.qburst.blaise.moneytracker.Activity.MainActivity.fragment_ID;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragment_ID = SETTINGS;
    }
}
