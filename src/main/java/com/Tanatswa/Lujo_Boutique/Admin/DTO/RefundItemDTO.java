package com.Tanatswa.Lujo_Boutique.Admin.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundItemDTO {
    private Integer refundItemId;
    private BigDecimal price;
    private Integer quantity;
    private String productName;
}

