package com.example.vision.UI.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vision.R;
import com.example.vision.UI.ViewModel.SettingsViewModel;


public class Settings extends Fragment {
    View view;
    SettingsViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_settings, container, false);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        return view;
    }
}