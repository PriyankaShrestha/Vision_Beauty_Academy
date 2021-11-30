package com.example.vision.Repository.User;

import androidx.lifecycle.LiveData;

import com.example.vision.Model.User;
import com.example.vision.PropertyChange.PropertyChangeSubject;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public interface UserRepository extends PropertyChangeSubject {
    void registerUser(User newUser);
    void login(String username, String password);
    LiveData<FirebaseUser> getCurrentUser();

    void logout();

    LiveData<ArrayList<User>> getAllUser();
}
