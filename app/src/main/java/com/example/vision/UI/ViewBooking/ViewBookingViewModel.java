package com.example.vision.UI.ViewBooking;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vision.Model.Booking;
import com.example.vision.Repository.Booking.BookingRepository;
import com.example.vision.Repository.Booking.BookingRepositoryImpl;

import java.util.ArrayList;

public class ViewBookingViewModel extends AndroidViewModel {

    private BookingRepository bookingRepo;
    private ArrayList<Booking> bookings;

    public ViewBookingViewModel(@NonNull Application app) {
        super(app);
        bookingRepo = new BookingRepositoryImpl(app);
    }

    public LiveData<ArrayList<Booking>> getAllBookings() {
        return bookingRepo.getAllBookings();
    }
}


