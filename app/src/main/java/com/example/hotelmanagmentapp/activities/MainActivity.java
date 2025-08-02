package com.example.hotelmanagmentapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotelmanagmentapp.R;
import com.example.hotelmanagmentapp.data.local.UserSharePreference;
import com.example.hotelmanagmentapp.data.remote.models.request.LoginRequest;
import com.example.hotelmanagmentapp.data.remote.models.response.login.LoginResponse;
import com.example.hotelmanagmentapp.data.remote.repository.AuthRepository;

public class MainActivity extends BaseActivity {
    private Button btnLogin,btnRegister;
    private TextView etWelcome;
    private AuthRepository authRepository = new AuthRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etWelcome = findViewById(R.id.tvDescription);
        String username = UserSharePreference.getUsername(this);
        etWelcome.setText("Welcome, " + username + "!");

        authRepository = new AuthRepository();
        LoginRequest request = new LoginRequest();
        request.setPhoneNumber("dinsarenkh33");
        request.setPassword("123456");
        authRepository.login(request, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister= findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}