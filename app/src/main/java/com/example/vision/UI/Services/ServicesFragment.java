package com.example.vision.UI.Services;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vision.R;
import com.example.vision.Model.Service;
import com.example.vision.Adapter.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;


public class ServicesFragment extends Fragment implements ServiceAdapter.onListItemListener {

    ServicesViewModel viewModel;
    RecyclerView recyclerView;
    ServiceAdapter adapter;

  @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_services, container, false);

        viewModel = new ViewModelProvider(this).get(ServicesViewModel.class);

        recyclerView = view.findViewById(R.id.servicesList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new ServiceAdapter(this);

        getAllServices();

        return view;
  }

  private void getAllServices() {
      viewModel.getAllServices().observe(getViewLifecycleOwner(), new Observer<ArrayList<Service>>() {
          @Override
          public void onChanged(ArrayList<Service> services) {
              adapter.setServiceItems(services);
              recyclerView.setAdapter(adapter);
          }
      });
  }

    @Override
    public void onClicked(Service service) {
        //TODO implement functionality when the service item is clicked
    }
}