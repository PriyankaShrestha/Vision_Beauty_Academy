package com.example.vision.Repository.Services;

import androidx.lifecycle.LiveData;

import com.example.vision.Model.Service;
import com.example.vision.PropertyChange.PropertyChangeSubject;

import java.util.ArrayList;

public interface ServiceRepository extends PropertyChangeSubject {
    void addService(Service service);
    ArrayList<Service> getServices();
    LiveData<ArrayList<Service>> getAllServices();
}
