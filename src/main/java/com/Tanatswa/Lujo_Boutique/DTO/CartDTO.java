package com.Tanatswa.Lujo_Boutique.DTO;

import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Integer cartId;
    private LocalDateTime createdAt;
    private BigDecimal total;
    private Integer customerId;
    private List<CartItemDTO> items;
}