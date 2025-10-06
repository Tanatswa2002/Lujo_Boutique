package com.example.lujosboutique1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";


    private EditText emailOrPhone, password, fullName;
    private Button btnSignUp, btnGoogleSignUp;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        emailOrPhone = findViewById(R.id.editTextEmailOrPhone);
        password = findViewById(R.id.editTextPassword);
        fullName = findViewById(R.id.editTextFullName);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnGoogleSignUp = findViewById(R.id.btnGoogleSignUp);

        tvLoginLink = findViewById(R.id.tvLoginLink);




        btnSignUp.setOnClickListener(v -> {
            String email = emailOrPhone.getText().toString().trim();
            String pass = password.getText().toString();
            String name = fullName.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty() || name.isEmpty()) {
                Toast.makeText(CreateAccountActivity.this, "Please ensure all fields are filled.", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d(TAG, "User attempting to register: " + name);
            Toast.makeText(CreateAccountActivity.this, "Attempting registration for: " + email, Toast.LENGTH_LONG).show();
        });


        btnGoogleSignUp.setOnClickListener(v -> {
            Toast.makeText(CreateAccountActivity.this, "Initiating Google Sign-Up.", Toast.LENGTH_SHORT).show();
        });


        tvLoginLink.setOnClickListener(v -> {

            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            startActivity(intent);

            
        });
    }
}
