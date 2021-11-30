package com.example.vision.Repository.Course;

import androidx.lifecycle.LiveData;

import com.example.vision.Model.Course;
import com.example.vision.PropertyChange.PropertyChangeSubject;

import java.util.ArrayList;

public interface CourseRepository extends PropertyChangeSubject {
    void addCourse(Course course);

    LiveData<ArrayList<Course>> getAllCourses();

    void addStudentToCourse(String courseName, String userName);
}
