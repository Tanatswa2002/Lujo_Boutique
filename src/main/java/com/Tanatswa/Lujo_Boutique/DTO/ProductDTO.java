package com.Tanatswa.Lujo_Boutique.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private Integer quantity;
    private String imageBase64;
    private String category;      // e.g., "Women", "Men", "Kids"
    private String subCategory;   // e.g., "Featured", "Trending"
    private Double rating;      // default: 4.5
    private Integer reviews;    // default: 120
    private Integer sold;       // default: 500+

}
