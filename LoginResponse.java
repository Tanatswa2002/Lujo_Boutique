package com.example.lujosboutique1.models;

/**
 * Data model for the response received after a successful login.
 * Field names must match the keys sent by your Spring Boot endpoint.
 */
public class LoginResponse {
    private String token; // Assuming your server returns a JWT or similar access token

    // You may also include user details like name, ID, etc.
    // private String userId;
    // private String name;

    // Getters and setters (required by Gson for deserialization)

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // public String getUserId() {
    //     return userId;
    // }

    // public void setUserId(String userId) {
    //     this.userId = userId;
    // }
}