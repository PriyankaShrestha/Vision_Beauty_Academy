package com.example.vision.Repository.Image;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.example.vision.Model.Image;
import com.example.vision.PropertyChange.PropertyChangeSubject;

import java.util.ArrayList;

public interface ImageRepository extends PropertyChangeSubject {
    void upload(Image img);
    public LiveData<ArrayList<String>> getAllImages();
}
