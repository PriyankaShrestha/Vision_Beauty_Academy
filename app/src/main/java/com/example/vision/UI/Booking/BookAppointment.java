package com.example.vision.UI.Booking;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vision.Adapter.BookingAdapter;
import com.example.vision.Model.Course;
import com.example.vision.Model.User;
import com.example.vision.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BookAppointment extends Fragment {

    private BookAppointmentViewModel viewModel;
    private Button book;
    private Button cancel;
    private TextView datePicker;
    private ListView bookingTime;
    private TextInputEditText specialRequest;
    private String pickedTime;
    DatePickerDialog.OnDateSetListener setListener;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book_appointment, container, false);

        viewModel = new ViewModelProvider(this).get(BookAppointmentViewModel.class);

        initViews();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getTime());
        bookingTime.setAdapter(adapter);

        bookingTime.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedTime = getTime().get(i);
            pickedTime = selectedTime;
        });

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener,
                        year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String selectedDate = dayOfMonth + "/" + month + "/" + year;
                datePicker.setText(selectedDate);
            }
        };

        book.setOnClickListener(v -> onBookClicked());
        cancel.setOnClickListener(v -> onCancelClicked());

        return view;
    }

    private void initViews() {
        book = view.findViewById(R.id.button_book);
        cancel = view.findViewById(R.id.button_cancel);
        datePicker = view.findViewById(R.id.date_picker);
        specialRequest = view.findViewById(R.id.special_request);
        bookingTime = view.findViewById(R.id.time_list);
    }

    private void onCancelClicked() {

    }

    private void onBookClicked() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser user) {
                viewModel.requestBooking(datePicker.getText().toString(), pickedTime, specialRequest.getText().toString(), user.getEmail());
            }
        });
    }

    private List<String> getTime() {
        return viewModel.getTime();
    }
}