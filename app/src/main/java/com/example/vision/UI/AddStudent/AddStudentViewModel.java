package com.example.vision.UI.AddStudent;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vision.Model.Course;
import com.example.vision.Model.User;
import com.example.vision.Repository.Course.CourseRepository;
import com.example.vision.Repository.Course.CourseRepositoryImpl;
import com.example.vision.Repository.User.UserRepository;
import com.example.vision.Repository.User.UserRepositoryImpl;

import java.util.ArrayList;

public class AddStudentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private CourseRepository courseRepository;

    public AddStudentViewModel(@NonNull Application app) {
        super(app);
        userRepository = new UserRepositoryImpl(app);
        courseRepository = new CourseRepositoryImpl(app);
    }


    public LiveData<ArrayList<Course>> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public LiveData<ArrayList<User>> getAllUsers() {
        return userRepository.getAllUser();
    }

    public void addStudentToCourse(String courseName, String userName) {
        if(!courseName.trim().equals("") && !userName.trim().equals("")) {
            courseRepository.addStudentToCourse(courseName, userName);
        }
        else Toast.makeText(getApplication(), "Please select all", Toast.LENGTH_SHORT).show();
    }
}
