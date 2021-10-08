package com.example.vision;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> products;
    onListItemClickListener listener;

    public ProductAdapter(List<Product> products, onListItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.products_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.productId.setText(String.valueOf(products.get(position).getProductId()));
        holder.name.setText(products.get(position).getName());
        holder.price.setText(String.valueOf(products.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        TextView productId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            productId = itemView.findViewById(R.id.productId);
            itemView.setOnClickListener(view -> listener.onClicked(products.get(getAdapterPosition())));
        }
    }

    public interface onListItemClickListener {
        void onClicked(Product prod);
    }
}
