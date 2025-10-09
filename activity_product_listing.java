package com.example.lujosboutique1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class activity_product_listing extends AppCompatActivity {

    private static final String TAG = "ProductListingActivity";

    private TextInputEditText etProductName;
    private TextInputEditText etProductDescription;
    private TextInputEditText etProductPrice;
    private Button btnUploadImage;
    private Button btnSaveListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to your XML layout file
        setContentView(R.layout.activity_product_listing);


        initViews();


        setupListeners();
    }



    private void initViews() {
        etProductName = findViewById(R.id.etProductName);
        etProductDescription = findViewById(R.id.etProductDescription);
        etProductPrice = findViewById(R.id.etProductPrice);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnSaveListing = findViewById(R.id.btnSaveListing);


    }


    private void setupListeners() {
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageUpload();
            }
        });

        btnSaveListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSaveListing();
            }
        });
    }


    private void handleImageUpload() {
        Toast.makeText(this, "Image upload feature coming soon!", Toast.LENGTH_SHORT).show();
        // TODO: Implement logic to open gallery/camera for image selection
        Log.d(TAG, "Upload Image button clicked.");
    }


    private void handleSaveListing() {
        String name = etProductName.getText().toString().trim();
        String description = etProductDescription.getText().toString().trim();
        String price = etProductPrice.getText().toString().trim();


        if (name.isEmpty() || description.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_LONG).show();


            return;
        }


        double productPrice = 0.0;
        try {
            productPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {

            Toast.makeText(this, "Invalid price format.", Toast.LENGTH_LONG).show();
            return;
        }


        Log.i(TAG, "Listing Name: " + name);
        Log.i(TAG, "Listing Description: " + description);
        Log.i(TAG, "Listing Price: $" + productPrice);

        // TODO: Implement actual saving logic (e.g., to a database, API call)

        Toast.makeText(this, "Product Listing Saved!", Toast.LENGTH_SHORT).show();

    }
}