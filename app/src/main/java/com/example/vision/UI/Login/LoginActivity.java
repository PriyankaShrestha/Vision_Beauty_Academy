package com.example.vision.UI.Login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vision.R;

import com.example.vision.UI.MainActivity.MainActivity;
import com.example.vision.UI.RegisterUser.RegisterUserActivity;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel viewModel;
    Button login;
    EditText username;
    EditText password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        initViews();

        checkIfSignedIn();
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null)
                navigateToMainActivity();
        });
    }

    private void initViews() {
        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.sign_up);

        login.setOnClickListener(v -> onLoginClicked());
        signUp.setOnClickListener(v -> onSignup());
    }

    private void onSignup() {
        startActivity(new Intent(this, RegisterUserActivity.class));
        finish();
    }

    private void onLoginClicked() {
        viewModel.login(username.getText().toString(), password.getText().toString());
        checkIfSignedIn();
    }

    private void navigateToMainActivity() {
       startActivity(new Intent(this, MainActivity.class));
       finish();
    }
}