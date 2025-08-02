package com.example.hotelmanagmentapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanagmentapp.R;
import com.example.hotelmanagmentapp.data.remote.models.request.RegisterRequest;

import com.example.hotelmanagmentapp.data.remote.models.response.register.RegisterResponse;
import com.example.hotelmanagmentapp.data.remote.repository.RegisterRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etUsername, etEmail, etPhoneNumber, etPassword, etConfirmPassword;
    private Button btnRegister;

    private RegisterRepository registerRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Initialize repository
        registerRepository = new RegisterRepository();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Get input values
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Validate input
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() ||
                email.isEmpty() || phoneNumber.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Build request
        RegisterRequest request = new RegisterRequest(
                firstName,
                lastName,
                username,
                email,
                phoneNumber,
                password,
                confirmPassword,
                "NON",   // default profile
                "USER"   // default role
        );

        // Call API
        registerRepository.register(request, new RegisterRepository.RegisterCallback() {
            @Override
            public void onSuccess(RegisterResponse response) {
                Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to login or main screen if needed
                finish();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(RegisterActivity.this, "Register failed: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
