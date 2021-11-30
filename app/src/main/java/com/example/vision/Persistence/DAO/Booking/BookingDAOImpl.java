package com.example.vision.Persistence.DAO.Booking;


import android.util.Log;


import androidx.lifecycle.LiveData;

import com.example.vision.CallBack.CallBack;
import com.example.vision.Model.Booking;

import com.example.vision.Model.Service;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class BookingDAOImpl extends LiveData<Booking> implements BookingDAO {

    private FirebaseFirestore db;
    private CallBack callBack;
    onInter onInter;

    public BookingDAOImpl(CallBack callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        this.onInter = onInter;
    }


    @Override
    public List<String> getTime() {
        List<String> time = new ArrayList<>();
        time.add("10:00");
        time.add("11:00");
        time.add("12:00");
        time.add("13:00");
        time.add("14:00");
        time.add("15:00");
        return time;
    }

    @Override
    public void requestBooking(Booking booking) {
        db.collection("bookings")
                .add(booking)
                .addOnSuccessListener(documentReference ->
                        callBack.getMessage("Booking Successful")
                )
                .addOnFailureListener(
                        e -> Log.w(" ", "Error adding document", e)
                );
    }

    @Override
    public void getBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();

        db.collection("bookings")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        Booking booking =  ds.toObject(Booking.class);
                        bookings.add(booking);
                        onInter.bookings(bookings);
                    }
                });
    }

    public interface onInter {
        void bookings(ArrayList<Booking> bookings);
    }
}
