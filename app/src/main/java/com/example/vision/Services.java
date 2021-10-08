package com.example.vision;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Services extends Fragment implements ServiceAdapter.onListItemListener{

    RecyclerView recyclerView;

  @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_services, container, false);

        recyclerView = view.findViewById(R.id.servicesList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        //TODO get the services from the database
        List<Service> servicesList = new ArrayList<>();

        servicesList.add(new Service("Threading", "Eyebrow and Upperlips threading", 50.00));
        servicesList.add(new Service("Eyebrow Tinting", "", 8000.00));


        ServiceAdapter adapter = new ServiceAdapter(servicesList, this);
        recyclerView.setAdapter(adapter);

        return view;
  }

    @Override
    public void onClicked(Service service) {
        //TODO implement functionality when the service item is clicked
    }
}