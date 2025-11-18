package com.Tanatswa.Lujo_Boutique.Controller;

import com.Tanatswa.Lujo_Boutique.DTO.CategoryDTO;
import com.Tanatswa.Lujo_Boutique.Domain.Category;
import com.Tanatswa.Lujo_Boutique.Domain.SubCategory;
import com.Tanatswa.Lujo_Boutique.DTO.ProductDTO;
import com.Tanatswa.Lujo_Boutique.Repository.CategoryRepository;
import com.Tanatswa.Lujo_Boutique.Repository.SubCategoryRepository;
import com.Tanatswa.Lujo_Boutique.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductService productService;

    // Get products for category (e.g., “Women”, “Kids”)
    @GetMapping("/category/{categoryId}/products")
    public List<ProductDTO> getProductsByCategory(@PathVariable Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) return List.of();
        return productService.getProductsByCategory(category);
    }

    // Get products for subcategory (e.g., “Featured”, “Trending”)
    @GetMapping("/subcategory/{subCategoryId}/products")
    public List<ProductDTO> getProductsBySubCategory(@PathVariable Integer subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElse(null);
        if (subCategory == null) return List.of();
        return productService.getProductsBySubCategory(subCategory);
    }

    // Search by name
    @GetMapping("/products/search")
    public List<ProductDTO> searchProducts(@RequestParam String query) {
        return productService.searchProducts(query);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(cat -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryId(cat.getCategoryId());
            dto.setName(cat.getName());
            dto.setImageUrl(cat.getImageUrl()); // if you have one
            dto.setItemCount(productService.getProductCountForCategory(cat));
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/categories/search")
    public List<CategoryDTO> searchCategories(@RequestParam String query) {
        return categoryRepository.findByNameContainingIgnoreCase(query).stream().map(cat -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryId(cat.getCategoryId());
            dto.setName(cat.getName());
            dto.setImageUrl(cat.getImageUrl());
            dto.setItemCount(productService.getProductCountForCategory(cat));
            return dto;
        }).collect(Collectors.toList());
    }

}
