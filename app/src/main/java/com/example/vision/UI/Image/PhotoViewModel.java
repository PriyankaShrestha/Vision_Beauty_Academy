package com.example.vision.UI.Image;

import android.app.Application;
import android.net.Uri;
import android.telecom.Call;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vision.CallBack.CallBack;
import com.example.vision.Model.Image;
import com.example.vision.Repository.Image.ImageRepository;
import com.example.vision.Repository.Image.ImageRepositoryImpl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class PhotoViewModel extends AndroidViewModel {

    private ImageRepository repo;

    public PhotoViewModel(@NonNull Application application) {
        super(application);
        repo = new ImageRepositoryImpl();
        repo.addPropertyChangeListener("MessageFromFirebase", this::message);
    }

    private void message(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    public void upload(Uri uri, String fileExtension) {
        Image img = new Image();
        img.setUrl(uri);
        img.setExtension(fileExtension);
        img.setName(System.currentTimeMillis() + "");
        repo.upload(img);
    }

    public LiveData<ArrayList<String>> getAllImages() {
        return repo.getAllImages();
    }
}
