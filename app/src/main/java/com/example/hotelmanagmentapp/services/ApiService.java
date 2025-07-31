package com.example.hotelmanagmentapp.services;

import com.example.hotelmanagmentapp.data.remote.models.request.LoginRequest;
import com.example.hotelmanagmentapp.data.remote.models.response.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    @POST("/api/oauth/token")
    Call<LoginResponse> login(@Body LoginRequest request);
}
