package com.example.vision.Persistence.DAO.User;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vision.CallBack.CallBack;
import com.example.vision.Model.Course;
import com.example.vision.Model.User;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class UserDAO extends LiveData<FirebaseUser> {
    private FirebaseAuth firebaseAuth;
    private final FirebaseAuth.AuthStateListener listener = firebaseAuth -> setValue(firebaseAuth.getCurrentUser());
    private FirebaseFirestore db;
    private CallBack callBack;
    private MutableLiveData<FirebaseUser> firebaseUser;

    onInter onInter;

    public UserDAO(CallBack callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = new MutableLiveData<>();
        this.onInter = onInter;
    }

    @Override
    protected void onActive() {
        super.onActive();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
    }

    public void insert(User newUser) {

        newUser.setRole("Visitor");

        firebaseAuth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        db.collection("users").document(newUser.getEmail()).set(newUser)
                                .addOnSuccessListener(documentReference -> {
                                    Log.i("Success message: ", "success");
                                    callBack.getMessage("Registration Completed");
                                })
                                .addOnFailureListener(e -> {
                                    Log.i("failure message: ", "failure");
                                    callBack.getMessage(task.getException().getMessage());
                                });
                    }
                    else {
                        callBack.getMessage(task.getException().getMessage());
                    }
                });
    }

    public void login(String username, String password) {
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        firebaseUser.postValue(firebaseAuth.getCurrentUser());
                    } else {
                        callBack.getMessage(task.getException().getMessage());
                    }
                });
    }

    public void getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        db.collection("users")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        User user =  ds.toObject(User.class);
                        users.add(user);
                        onInter.users(users);
                    }
                });
    }


    public interface onInter {
        void users(ArrayList<User> users);
    }
}
