package com.Tanatswa.Lujo_Boutique.Repository;

import com.Tanatswa.Lujo_Boutique.Domain.Product;
import com.Tanatswa.Lujo_Boutique.Domain.Category;
import com.Tanatswa.Lujo_Boutique.Domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    // All products in a specific category
    List<Product> findBySubCategory_Category(Category category);

    // All products in a specific subcategory
    List<Product> findBySubCategory(SubCategory subCategory);

    // Search products by name
    List<Product> findByProductNameContainingIgnoreCase(String name);
    int countBySubCategory_Category(Category category);
}
