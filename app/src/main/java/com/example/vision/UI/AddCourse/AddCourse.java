package com.example.vision.UI.AddCourse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vision.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddCourse extends Fragment {

    private AddCourseViewModel viewModel;
    private TextInputEditText name;
    private TextInputEditText startDate;
    private TextInputEditText endDate;
    private TextInputEditText content;
    private Button addCourse;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_course, container, false);

        initViews();

        viewModel = new ViewModelProvider(this).get(AddCourseViewModel.class);

        addCourse.setOnClickListener(v -> add());
        return view;
    }

    private void initViews() {
        name = view.findViewById(R.id.input_course_name);
        content = view.findViewById(R.id.input_content);
        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        addCourse = view.findViewById(R.id.save);
    }

    private void add() {
        viewModel.addCourse(name.getText().toString(), content.getText().toString(),
                startDate.getText().toString(), endDate.getText().toString());
    }


}