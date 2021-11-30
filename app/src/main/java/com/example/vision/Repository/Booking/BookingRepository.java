package com.example.vision.Repository.Booking;

import androidx.lifecycle.LiveData;

import com.example.vision.Model.Booking;
import com.example.vision.PropertyChange.PropertyChangeSubject;

import java.util.ArrayList;


public interface BookingRepository extends PropertyChangeSubject {
    void requestBooking(Booking booking);

    LiveData<ArrayList<Booking>> getAllBookings();
}
