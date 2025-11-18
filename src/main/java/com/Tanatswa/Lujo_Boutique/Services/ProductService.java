package com.Tanatswa.Lujo_Boutique.Services;

import com.Tanatswa.Lujo_Boutique.Domain.Product;
import com.Tanatswa.Lujo_Boutique.Domain.Category;
import com.Tanatswa.Lujo_Boutique.Domain.SubCategory;
import com.Tanatswa.Lujo_Boutique.DTO.ProductDTO;
import com.Tanatswa.Lujo_Boutique.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private ProductDTO convertToDTO(Product product) {
        String imageBase64 = product.getImage() != null
                ? Base64.getEncoder().encodeToString(product.getImage())
                : null;

        return ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())      // quantity field only
                .imageBase64(imageBase64)
                .category(product.getSubCategory().getCategory().getName())
                .subCategory(product.getSubCategory().getName())
                .rating(4.5)
                .reviews(129)
                .sold(527)
                .build();
    }

    public ProductDTO getProductById(String productId) {
        return productRepository.findById(productId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<ProductDTO> getProductsByCategory(Category category) {
        return productRepository.findBySubCategory_Category(category)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsBySubCategory(SubCategory subCategory) {
        return productRepository.findBySubCategory(subCategory)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> searchProducts(String query) {
        return productRepository.findByProductNameContainingIgnoreCase(query)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public int getProductCountForCategory(Category category) {
        return productRepository.countBySubCategory_Category(category);
    }
}
