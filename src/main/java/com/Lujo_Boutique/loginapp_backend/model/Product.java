package com.Lujo_Boutique.loginapp_backend.model;

import jakarta.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "product_id", length = 15)
    private String productId;

    @Column(name = "product_description", nullable = false, columnDefinition = "TEXT")
    private String productDescription;

    @Lob
    @Column(name = "image")
    private byte[] image;

    // Constructors
    public Product() {}

    public Product(String productId, String productDescription, byte[] image) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.image = image;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", image=" + (image != null ? Arrays.toString(Arrays.copyOf(image, 10)) + "..." : "null") +
                '}';
    }
}

