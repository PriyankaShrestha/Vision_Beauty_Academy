package com.example.vision.Persistence.DAO.Course;

import com.example.vision.Model.Course;

public interface CourseDao {
    void addCourse(Course course);

    void getCourses();

    void addStudentToCourse(String courseName, String userName);
}
