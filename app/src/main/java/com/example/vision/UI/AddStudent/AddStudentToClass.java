package com.example.vision.UI.AddStudent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.vision.Adapter.CourseAdapter;
import com.example.vision.Model.Course;
import com.example.vision.Model.User;
import com.example.vision.R;

import java.util.ArrayList;
import java.util.List;

public class AddStudentToClass extends Fragment {

    private View view;
    private Spinner spinner;
    private ListView userList;
    private Button add;
    private AddStudentViewModel viewModel;

    private ArrayList<Course> courses;
    private ArrayList<User> users;

    private String selectedUsername;
    private String selectedCourseName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_add_student_to_class, container, false);

        viewModel = new ViewModelProvider(this).get(AddStudentViewModel.class);
        initViews();

        setSpinner();

        setUsers();

        add.setOnClickListener(v -> onAdd());

        return view;
    }

    private void onAdd() {
        //viewModel.addStudentToCourse(selectedCourseName, selectedUsername);
    }

    private void setUsers() {
        viewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> us) {
                users = us;
                List<String> username = new ArrayList<>();
                for (User u: users) {
                    username.add(u.getEmail());
                }
                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, username);
                userList.setAdapter(myAdapter);
                userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedUsername = username.get(i);
                    }
                });
            }
        });
    }

    private void setSpinner() {
        viewModel.getAllCourses().observe(getViewLifecycleOwner(), new Observer<ArrayList<Course>>() {
            @Override
            public void onChanged(ArrayList<Course> cs) {
                courses = cs;
            List<String> courseName = new ArrayList<>();
            for (Course c: courses) {
                courseName.add(c.getName());
            }
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_list_item_1, courseName);
            myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner.setAdapter(myAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedCourseName = spinner.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            }
        });
    }

    private void initViews() {
        spinner = view.findViewById(R.id.spinner);
        userList = view.findViewById(R.id.userList);
        add = view.findViewById(R.id.button_add);
    }
}