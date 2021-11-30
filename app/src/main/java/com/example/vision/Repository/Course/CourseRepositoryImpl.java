package com.example.vision.Repository.Course;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vision.CallBack.CallBack;
import com.example.vision.Model.Course;
import com.example.vision.Persistence.DAO.Course.CourseDao;
import com.example.vision.Persistence.DAO.Course.CourseDaoImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CourseRepositoryImpl implements CourseRepository, CallBack, CourseDaoImpl.onInter {

    private CourseDao courseDao;
    private PropertyChangeSupport support;
    private MutableLiveData<ArrayList<Course>> mutableLiveData;

    public CourseRepositoryImpl(Application app) {
        courseDao = new CourseDaoImpl(this, this);
        support = new PropertyChangeSupport(this);
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    @Override
    public LiveData<ArrayList<Course>> getAllCourses() {
        getCourses();
        return mutableLiveData;
    }

    @Override
    public void addStudentToCourse(String courseName, String userName) {
        courseDao.addStudentToCourse(courseName, userName);
    }

    private void getCourses() {
        courseDao.getCourses();
    }

    @Override
    public void getMessage(String message) {
        support.firePropertyChange("MessageFromFirebase", null, message);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removerPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public void courses(ArrayList<Course> courses) {
        mutableLiveData.setValue(courses);
    }
}
