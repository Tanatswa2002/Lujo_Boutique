package com.Lujo_Boutique.loginapp_backend.controller;

import com.Lujo_Boutique.loginapp_backend.model.Product;
import com.Lujo_Boutique.loginapp_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add a product (with image upload)
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(
            @RequestParam("product_id") String productId,
            @RequestParam("product_description") String productDescription,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        try {
            byte[] imageData = imageFile != null ? imageFile.getBytes() : null;

            Product product = new Product(productId, productDescription, imageData);
            productService.addProduct(product);
            return ResponseEntity.ok("Product added successfully!");

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing image: " + e.getMessage());
        }
    }

    // Get all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get single product
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
