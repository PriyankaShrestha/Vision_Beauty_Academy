package com.example.vision.UI.MainActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.vision.Repository.User.UserRepository;
import com.example.vision.Repository.User.UserRepositoryImpl;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepo;

    public MainActivityViewModel(@NonNull Application app) {
        super(app);
        userRepo = new UserRepositoryImpl(app);
    }

    public void logout() {
        userRepo.logout();
    }
}
