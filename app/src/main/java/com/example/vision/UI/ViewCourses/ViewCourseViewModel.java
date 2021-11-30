package com.example.vision.UI.ViewCourses;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vision.Model.Course;
import com.example.vision.Repository.Course.CourseRepository;
import com.example.vision.Repository.Course.CourseRepositoryImpl;

import java.util.ArrayList;

public class ViewCourseViewModel extends AndroidViewModel {

    private CourseRepository courseRepository;

    public ViewCourseViewModel(@NonNull Application app) {
        super(app);
        courseRepository = new CourseRepositoryImpl(app);
    }

    public LiveData<ArrayList<Course>> getAllCourses() {
        return courseRepository.getAllCourses();
    }
}
