package com.example.vision.UI.Services;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vision.Model.Service;
import com.example.vision.Repository.Services.ServiceRepository;
import com.example.vision.Repository.Services.ServiceRepositoryImpl;

import java.util.ArrayList;

public class ServicesViewModel extends AndroidViewModel {

    private ServiceRepository serviceRepository;
    private ArrayList<Service> services;

    public ServicesViewModel(@NonNull Application app) {
        super(app);
        serviceRepository = new ServiceRepositoryImpl();
    }

   /* public ArrayList<Service> getServices() {
        serviceRepository.getServices();
        return services;
    }

    */

    public LiveData<ArrayList<Service>> getAllServices() {
        return serviceRepository.getAllServices();
    }
}
