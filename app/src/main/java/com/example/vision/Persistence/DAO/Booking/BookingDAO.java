package com.example.vision.Persistence.DAO.Booking;

import com.example.vision.Model.Booking;

import java.util.List;

public interface BookingDAO {
    void requestBooking(Booking booking);

    List<String> getTime();

    void getBookings();
}
