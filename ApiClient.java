package com.example.lujosboutique1.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class to manage the Retrofit client instance.
 * All API calls will use this client.
 */
public class ApiClient {

    // ⚠️ IMPORTANT: Replace this with the actual base URL of your Spring Boot server.
    // Use your computer's local IP address (e.g., 192.168.1.XX) or "http://10.0.2.2"
    // for an Android Emulator to access the local host (127.0.0.1).
    private static final String BASE_URL = "http://10.0.2.2:8080/api/v1/";

    private static Retrofit retrofit = null;

    /**
     * Gets the configured Retrofit instance (Singleton pattern).
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}