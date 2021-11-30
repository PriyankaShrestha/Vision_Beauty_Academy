package com.example.vision.Persistence.DAO.Course;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.vision.CallBack.CallBack;
import com.example.vision.Model.Course;
import com.example.vision.Model.Service;
import com.example.vision.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class CourseDaoImpl extends LiveData<Course> implements CourseDao{

    private FirebaseFirestore db;
    private CallBack callBack;
    onInter onInter;
    private User user;

    public CourseDaoImpl(CallBack callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        this.onInter = onInter;
    }

    @Override
    public void addCourse(Course course) {
        db.collection("courses")
                .document(course.getName()).set(course)
                .addOnSuccessListener(documentReference ->
                        callBack.getMessage("New course added!")
                )
                .addOnFailureListener(
                        e -> Log.w(" ", "Error adding document", e)
                );
    }

    @Override
    public void getCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        db.collection("courses")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        Course course =  ds.toObject(Course.class);
                        courses.add(course);
                        onInter.courses(courses);
                    }
                });
    }

    private void getUser(String username) {
      /*  db.collection("users").document(username)
                .update("Role", "Student").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                setUser(username);
                Log.d("Successful", "DocumentSnapshot successfully updated!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Failure", "Error updating document", e);
            }
        });

       */
    }

    @Override
    public void addStudentToCourse(String courseName, String userName) {
      /*  getUser(userName);


        db.collection("courses").document(courseName)
            .update("students", student).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("Successful", "DocumentSnapshot successfully updated!");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("Failure", "Error updating document", e);
                }
            });

       */
    }



    private void setUser(String username) {
      /*  db.collection("users")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        User u =  ds.toObject(User.class);
                        if(u.getName().equals(username)) {
                            user = u;
                            Log.i("converted user", "setUser: " + student);
                            break;
                        }
                    }
                });

       */
    }


    public interface onInter {
        void courses(ArrayList<Course> courses);
    }
}
