package com.example.vision.Persistence.DAO.Service;

import androidx.lifecycle.LiveData;

import com.example.vision.Model.Service;

import java.util.ArrayList;

public interface ServiceDao {
    void addService(Service service);

    ArrayList<Service> getServices();
}
