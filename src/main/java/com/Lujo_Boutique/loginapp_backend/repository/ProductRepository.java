package com.Lujo_Boutique.loginapp_backend.repository;

import com.Lujo_Boutique.loginapp_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {
}
