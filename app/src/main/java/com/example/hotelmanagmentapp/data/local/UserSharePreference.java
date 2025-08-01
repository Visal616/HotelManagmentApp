package com.example.hotelmanagmentapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hotelmanagmentapp.data.remote.models.response.login.LoginResponse;

public class UserSharePreference {
    public static void saveToken(LoginResponse loginResponse, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User_DB",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("access_token", loginResponse.getAccessToken());
        editor.putString("refresh_token", loginResponse.getRefreshToken());
        editor.putString("username", loginResponse.getUser().getUsername());
        editor.apply();
    }

    public static String getAccessToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User_DB", context.MODE_PRIVATE);
        return sharedPreferences.getString("access_token","");
    }

    public static String getRefreshToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User_DB", context.MODE_PRIVATE);
        return sharedPreferences.getString("refresh_token","");
    }

    public static String getUsername(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User_DB", context.MODE_PRIVATE);
        return sharedPreferences.getString("username","");
    }

}
