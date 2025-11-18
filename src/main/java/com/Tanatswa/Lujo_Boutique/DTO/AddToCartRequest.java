package com.Tanatswa.Lujo_Boutique.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {
    private String productId;
    private Integer quantity;
}
