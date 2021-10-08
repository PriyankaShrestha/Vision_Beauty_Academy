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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Products extends Fragment implements ProductAdapter.onListItemClickListener {

    RecyclerView recyclerView;
    List<Product> productsList  = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.frag_products, container, false);

        recyclerView = view.findViewById(R.id.productsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        productsList.add(new Product("TRESemme", 500, 1));
        productsList.add(new Product("L'Oreal Paris", 455, 2));
        productsList.add(new Product("Dove", 500, 3));
        productsList.add(new Product("Pantene", 200, 4));
        productsList.add(new Product("Himalaya", 365, 5));
        productsList.add(new Product("Biotique", 500, 6));
        productsList.add(new Product("Head & Shoulders", 615, 7));

        ProductAdapter adapter = new ProductAdapter(productsList, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCLicked(Product prod) {
        // do something
    }
}