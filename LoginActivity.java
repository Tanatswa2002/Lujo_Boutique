package com.example.lujosboutique1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// IMPORTS REQUIRED FOR THE CONNECTION (Retrofit)
import com.example.lujosboutique1.api.ApiClient;
import com.example.lujosboutique1.api.ApiService;
import com.example.lujosboutique1.models.LoginRequest;
import com.example.lujosboutique1.models.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText emailOrPhone, password;
    private Button btnLogin;
    private TextView tvForgotPassword, tvSignUpLink;
    private ImageButton btnGoogleSignIn;


    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 1. Initialize the network service client
        // This sets up Retrofit to talk to your Spring Boot server.
        apiService = ApiClient.getClient().create(ApiService.class);

        // Initialize UI components... (omitted for brevity)
        emailOrPhone = findViewById(R.id.editTextLoginEmail);
        password = findViewById(R.id.editTextLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);
        tvSignUpLink = findViewById(R.id.tvSignUpLink);

        // Set up the Login button click listener
        btnLogin.setOnClickListener(v -> {
            String email = emailOrPhone.getText().toString().trim();
            String pass = password.getText().toString();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please enter email/phone and password.", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d(TAG, "Attempting login for: " + email);


            performLogin(email, pass);
        });


        tvForgotPassword.setOnClickListener(v -> Toast.makeText(this, "Redirecting to Password Reset screen.", Toast.LENGTH_SHORT).show());
        btnGoogleSignIn.setOnClickListener(v -> Toast.makeText(this, "Initiating Google Sign-In.", Toast.LENGTH_SHORT).show());
        tvSignUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }





    private void performLogin(String identifier, String password) {

        LoginRequest loginRequest = new LoginRequest(identifier, password);



        Call<LoginResponse> call = apiService.login(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    Log.d(TAG, "Login Successful. Token: " + response.body().getToken());

                    Toast.makeText(LoginActivity.this, "Login successful! Redirecting to Home.", Toast.LENGTH_SHORT).show();


                    Intent homeIntent = new Intent(LoginActivity.this, HomeOverviewActivity.class);

                    startActivity(homeIntent);
                    finish();
                } else {

                    String error = (response.code() == 401) ?
                            "Invalid email/phone or password." :
                            "Authentication failed. Server responded with code: " + response.code();

                    Log.e(TAG, "Login Failed. Code: " + response.code());
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                
                Log.e(TAG, "Network Error: " + t.getMessage(), t);
                Toast.makeText(LoginActivity.this, "Connection Error. Could not connect to the server.", Toast.LENGTH_LONG).show();
            }
        });
    }
}

