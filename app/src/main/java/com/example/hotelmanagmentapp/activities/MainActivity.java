package com.example.hotelmanagmentapp.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotelmanagmentapp.R;
import com.example.hotelmanagmentapp.data.remote.models.request.LoginRequest;
import com.example.hotelmanagmentapp.data.remote.models.response.login.LoginResponse;
import com.example.hotelmanagmentapp.data.remote.repository.AuthRepository;

public class MainActivity extends BaseActivity {
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
    }
}