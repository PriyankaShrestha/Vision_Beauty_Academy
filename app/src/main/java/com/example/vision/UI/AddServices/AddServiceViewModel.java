package com.example.vision.UI.AddServices;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.vision.Model.Service;
import com.example.vision.Repository.Services.ServiceRepository;
import com.example.vision.Repository.Services.ServiceRepositoryImpl;

import java.beans.PropertyChangeEvent;

public class AddServiceViewModel extends AndroidViewModel {

    private ServiceRepository serviceRepository;

    public AddServiceViewModel(@NonNull Application app) {
        super(app);
        serviceRepository = new ServiceRepositoryImpl();
        serviceRepository.addPropertyChangeListener("MessageFromFirebase", this::message);
    }

    private void message(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    public void addService(String serviceName, double price, String description) {
        Service service = new Service();
        service.setService_name(serviceName);
        service.setPrice(price);
        service.setDescription(description);
        serviceRepository.addService(service);
    }
}
