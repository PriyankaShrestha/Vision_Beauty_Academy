package com.example.vision.UI.ViewBooking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vision.Adapter.BookingAdapter;
import com.example.vision.Model.Booking;
import com.example.vision.R;

import java.util.ArrayList;

public class ViewBookingFragment extends Fragment {

    private ViewBookingViewModel viewModel;
    RecyclerView recyclerView;
    BookingAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_booking, container, false);

        viewModel = new ViewModelProvider(this).get(ViewBookingViewModel.class);
        recyclerView = view.findViewById(R.id.bookingList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new BookingAdapter();

        getAllBookings();

        return view;
    }

    private void getAllBookings() {
        viewModel.getAllBookings().observe(getViewLifecycleOwner(), new Observer<ArrayList<Booking>>() {

            @Override
            public void onChanged(ArrayList<Booking> bookings) {
                adapter.setBookingItems(bookings);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}