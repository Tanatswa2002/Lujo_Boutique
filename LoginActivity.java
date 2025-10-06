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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";


    private EditText emailOrPhone, password;
    private Button btnLogin;
    private TextView tvForgotPassword, tvSignUpLink;
    private ImageButton btnGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        emailOrPhone = findViewById(R.id.editTextLoginEmail);
        password = findViewById(R.id.editTextLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);
        tvSignUpLink = findViewById(R.id.tvSignUpLink);




        btnLogin.setOnClickListener(v -> {
            String email = emailOrPhone.getText().toString().trim();
            String pass = password.getText().toString();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please enter email and password.", Toast.LENGTH_SHORT).show();
                return;
            }


            Log.d(TAG, "Attempting login for: " + email);


            Toast.makeText(this, "Login successful! Redirecting to Home.", Toast.LENGTH_SHORT).show();

            Intent homeIntent = new Intent(LoginActivity.this, HomeOverviewActivity.class);
            startActivity(homeIntent);

            finish();
        });


        tvForgotPassword.setOnClickListener(v -> {
            Toast.makeText(this, "Redirecting to Password Reset screen.", Toast.LENGTH_SHORT).show();
        });


        btnGoogleSignIn.setOnClickListener(v -> {
            Toast.makeText(this, "Initiating Google Sign-In.", Toast.LENGTH_SHORT).show();
        });


        tvSignUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }
}
