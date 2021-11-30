package com.example.vision.UI.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vision.R;
import com.example.vision.UI.ViewModel.FrontViewModel;

public class FrontPage extends Fragment {

    FrontViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.front_page, container, false);

        viewModel = new ViewModelProvider(this).get(FrontViewModel.class);

        return view;
    }
}
