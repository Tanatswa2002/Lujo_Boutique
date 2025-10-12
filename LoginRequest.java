package com.example.lujosboutique1.models;

/**
 * Data model for the user login request body.
 * Field names must match the keys expected by your Spring Boot endpoint.
 */
public class LoginRequest {
    private String identifier; // This is used for email or phone
    private String password;

    public LoginRequest(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    // Getters and setters (required by Gson for serialization)

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
