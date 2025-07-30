package com.example.hotelmanagmentapp.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanagmentapp.data.local.UserSharePreference;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        if(UserSharePreference.getAccessToken(this).isEmpty()){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
