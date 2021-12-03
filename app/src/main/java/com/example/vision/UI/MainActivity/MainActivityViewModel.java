package com.example.vision.UI.MainActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vision.Repository.User.UserRepository;
import com.example.vision.Repository.User.UserRepositoryImpl;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepo;

    public MainActivityViewModel(@NonNull Application app) {
        super(app);
        userRepo = new UserRepositoryImpl(app);
    }

    public void logout() {
        userRepo.logout();
    }

    public LiveData<FirebaseUser> getUsername() {
        return userRepo.getCurrentUser();
    }
}
