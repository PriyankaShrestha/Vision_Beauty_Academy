package com.example.vision.UI.Booking;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vision.Model.Booking;
import com.example.vision.Repository.Booking.BookingRepository;
import com.example.vision.Repository.Booking.BookingRepositoryImpl;
import com.example.vision.Repository.User.UserRepository;
import com.example.vision.Repository.User.UserRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class BookAppointmentViewModel extends AndroidViewModel {

    private BookingRepository repo;
    private UserRepository userRepository;


    public BookAppointmentViewModel(Application app) {
        super(app);
        repo = new BookingRepositoryImpl(app);
        userRepository = new UserRepositoryImpl(app);
        repo.addPropertyChangeListener("MessageFromFirebase", this::message);
    }

    private void message(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    public List<String> getTime() {
        ArrayList<String> time = new ArrayList<>();
        time.add("10:00");
        time.add("11:00");
        time.add("12:00");
        time.add("13:00");
        time.add("14:00");
        time.add("15:00");
        return time;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void requestBooking(String date, String time, String specialRequest, String username) {
        if(!date.trim().equals("") && !time.trim().equals("") && !specialRequest.trim().equals("")) {
            Booking booking = new Booking();
            booking.setDate(date);
            booking.setTime(time);
            booking.setSpecialRequest(specialRequest);
            booking.setUser(username);
            repo.requestBooking(booking);
        }
        else Toast.makeText(getApplication(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
    }
}
