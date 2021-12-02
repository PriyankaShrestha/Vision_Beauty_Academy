package com.example.vision.Persistence.DAO.Image;

import android.net.Uri;
import android.telecom.Call;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.vision.CallBack.CallBack;
import com.example.vision.Model.Booking;
import com.example.vision.Model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhotoDao extends LiveData<String> {

    private StorageReference storageReference;;
    private FirebaseFirestore db;
    private Map<String, String> imageMap;
    private onInter onInter;
    private CallBack callBack;

    public PhotoDao(onInter onInter, CallBack callBack) {
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        db = FirebaseFirestore.getInstance();
        imageMap = new HashMap<>();
        this.onInter = onInter;
        this.callBack = callBack;
    }

    public void upload(Image image) {
        StorageReference fileStorage = storageReference.child(image.getName() + "." + image.getExtension());
        fileStorage.putFile(image.getUrl()).addOnSuccessListener(taskSnapshot -> {
            String newUpload = taskSnapshot.getTask().toString();
            imageMap.put("url", newUpload);

            db.collection("uploads").add(imageMap);
            Log.d("Successful", "Successful");
            callBack.getMessage("Successfully uploaded");
        }).addOnFailureListener(e -> {
            Log.d("Fail", "Fail");
            callBack.getMessage(e.getMessage());
        });
    }

    public void retrieve() {
        ArrayList<String> image = new ArrayList<>();
        db.collection("uploads")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        String img = ds.getString("url");
                        image.add(img);
                        onInter.images(image);
                    }
                });
    }

    public interface onInter {
        void images(ArrayList<String> images);
    }
}
