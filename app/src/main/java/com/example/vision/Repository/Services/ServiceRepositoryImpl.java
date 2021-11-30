package com.example.vision.Repository.Services;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vision.CallBack.CallBack;
import com.example.vision.Model.Service;
import com.example.vision.Persistence.DAO.Service.ServiceDao;
import com.example.vision.Persistence.DAO.Service.ServiceDaoImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServiceRepositoryImpl implements ServiceRepository, CallBack, ServiceDaoImpl.onInter {

    private PropertyChangeSupport support;
    private ServiceDao serviceDao;
    private MutableLiveData<ArrayList<Service>> mutableLiveData;

    public ServiceRepositoryImpl() {
        support = new PropertyChangeSupport(this);
        serviceDao = new ServiceDaoImpl(this, this);
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public LiveData<ArrayList<Service>> getAllServices() {
        getServices();
        return mutableLiveData;
    }

    @Override
    public void addService(Service service) {
        serviceDao.addService(service);
    }

    @Override
    public ArrayList<Service> getServices() {
        return serviceDao.getServices();
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
    public void getMessage(String message) {
        support.firePropertyChange("MessageFromFirebase", null, message);
    }

    @Override
    public void services(ArrayList<Service> services) {
        mutableLiveData.setValue(services);
    }
}
