package com.example.vision.Persistence.DAO.Service;

import android.util.Log;


import androidx.lifecycle.LiveData;

import com.example.vision.CallBack.CallBack;

import com.example.vision.Model.Service;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class ServiceDaoImpl extends LiveData<Service> implements ServiceDao {

    private FirebaseFirestore db;
    private CallBack callBack;
    onInter onInter;


    public ServiceDaoImpl(CallBack callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        this.onInter = onInter;

    }

    @Override
    public void addService(Service service) {
        db.collection("services").document(service.getService_name())
                .set(service)
                .addOnSuccessListener(documentReference -> {
                    Log.i("Success message: ", "success");
                    callBack.getMessage("Service added");
                })
                .addOnFailureListener(e -> {
                    Log.i("failure message: ", "failure");
                    callBack.getMessage(e.getMessage());
                });
    }

    @Override
    public ArrayList<Service> getServices() {
        ArrayList<Service> services = new ArrayList<>();
       /* db.collection("services")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            services.add(document.toObject(Service.class));
                            listCallBack.getList(services);
                            Log.d("Successful", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.d("Error", "Error getting documents: ", task.getException());
                    }
                });*/

        db.collection("services")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        Service service =  ds.toObject(Service.class);
                        services.add(service);
                        onInter.services(services);
                    }
                });
        // not getting the obj
        return services;
    }

    public interface onInter {
        void services(ArrayList<Service> services);
    }
}
