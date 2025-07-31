package com.example.hotelmanagmentapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotelmanagmentapp.R;
import com.example.hotelmanagmentapp.data.local.UserSharePreference;
import com.example.hotelmanagmentapp.data.remote.models.request.LoginRequest;
import com.example.hotelmanagmentapp.data.remote.models.response.login.LoginResponse;
import com.example.hotelmanagmentapp.data.remote.repository.AuthRepository;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private AuthRepository authRepository = new AuthRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
        btnLogin= findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progress_circular);
        authRepository = new AuthRepository();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    private void login(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty()){
            showMessage("Please inter email and password");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        loginRequest.setPhoneNumber(email);
        authRepository.login(loginRequest, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
               if (loginResponse.getAccessToken() != null){
                   showMessage("Login successfully");
                   progressBar.setVisibility(View.GONE);
                   UserSharePreference.saveToken(loginResponse,LoginActivity.this);
                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                   startActivity(intent);
                   finish();
               }

            }

            @Override
            public void onFailure(String errorMessage) {
                showMessage("Login failed" + errorMessage);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}