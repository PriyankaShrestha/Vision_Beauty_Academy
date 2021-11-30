package com.example.vision.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vision.R;
import com.example.vision.Model.Service;

import java.util.ArrayList;


public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    ArrayList<Service> services;
    onListItemListener listener;


    public ServiceAdapter(onListItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.service_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        holder.service_name.setText(services.get(position).getService_name());
        holder.description.setText(services.get(position).getDescription());
        holder.price.setText(String.valueOf(services.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public void setServiceItems(ArrayList<Service> servicesList) {
        this.services = servicesList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView service_name;
        TextView description;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            service_name = itemView.findViewById(R.id.service_name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.service_price);
            itemView.setOnClickListener(view -> listener.onClicked(services.get(getAdapterPosition())));
        }
    }

    public interface onListItemListener {
        void onClicked(Service service);
    }
}
