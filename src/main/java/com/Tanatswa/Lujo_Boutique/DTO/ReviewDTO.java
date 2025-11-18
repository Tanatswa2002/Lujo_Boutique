package com.Tanatswa.Lujo_Boutique.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Integer reviewId;
    private String reviewDescription;
    private Integer customerId;
    private String productId;
}

