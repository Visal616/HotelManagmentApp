package com.example.hotelmanagmentapp.data.remote.repository;

import com.example.hotelmanagmentapp.data.remote.models.request.RegisterRequest;
import com.example.hotelmanagmentapp.data.remote.models.response.register.RegisterResponse;
import com.example.hotelmanagmentapp.services.ApiClient;
import com.example.hotelmanagmentapp.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {

    private ApiService apiService;

    public RegisterRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void register(RegisterRequest request, final RegisterCallback callback) {
        apiService.register(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Register failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface RegisterCallback {
        void onSuccess(RegisterResponse response);
        void onFailure(String errorMessage);
    }
}
