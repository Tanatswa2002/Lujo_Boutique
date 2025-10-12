package com.example.lujosboutique1.api;

import com.example.lujosboutique1.models.LoginRequest;
import com.example.lujosboutique1.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit Interface that defines all the API endpoints for the application.
 */
public interface ApiService {

    /**
     * Endpoint for user login.
     * It sends a POST request with the user's credentials (LoginRequest)
     * and expects a response (LoginResponse) from the server.
     * The path "/auth/login" should match your Spring Boot controller's mapping.
     */
    @POST("auth/login") // ⚠️ Replace "auth/login" with the actual endpoint path of your Spring Boot API
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // You would add other endpoints (e.g., register, get products) here...
    // @GET("products")
    // Call<List<Product>> getProducts();
}
