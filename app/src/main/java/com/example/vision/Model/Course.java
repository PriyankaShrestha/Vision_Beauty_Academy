package com.example.vision.Model;

import java.util.ArrayList;

public class Course {
    private String name;
    private String contents;
    private ArrayList<User> students = new ArrayList<>();
    private String startDate;
    private String endDate;

    public Course() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public ArrayList<User> getStudents() {
        return students;
    }

    public void addStudent(User student) {
        students.add(student);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
