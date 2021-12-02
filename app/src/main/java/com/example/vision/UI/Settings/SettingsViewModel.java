package com.example.vision.UI.Settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.vision.Repository.User.UserRepository;
import com.example.vision.Repository.User.UserRepositoryImpl;

public class SettingsViewModel extends AndroidViewModel {

    private UserRepository repo;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        repo = new UserRepositoryImpl(application);
    }

    public void deleteProfile() {
        repo.deleteProfile();
        repo.logout();
    }
}
