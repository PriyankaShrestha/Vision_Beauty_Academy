package com.example.vision;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    List<Service> services;
    onListItemListener listener;


    public ServiceAdapter(List<Service> services, onListItemListener listener) {
        this.services = services;
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
