package com.example.vision.UI.Image;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.example.vision.Adapter.GallaryAdapter;
import com.example.vision.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private GallaryAdapter adapter;
    private RecyclerView recyclerView;
    private PhotoViewModel viewModel;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_my_profile);

        viewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        recyclerView = findViewById(R.id.photo_booth);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new GallaryAdapter(this);

        setGallary();

        FloatingActionButton upload = findViewById(R.id.upload);

        upload.setOnClickListener(v -> uploadClicked());
    }


    private void uploadClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();
        }

        upload();
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void upload() {
        if(uri != null) {
            viewModel.upload(uri, getFileExtension(uri));
        }
    }

    private void setGallary() {
        viewModel.getAllImages().observe(this, string -> {
            adapter.setImageItems(string);
            recyclerView.setAdapter(adapter);
        });
    }
}