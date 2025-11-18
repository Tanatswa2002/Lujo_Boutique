package com.Tanatswa.Lujo_Boutique.DTO;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CheckoutResponse {
    private Integer orderId;
    private String orderStatus;
    private String deliveryOption;
    private BigDecimal cartTotal;
    private BigDecimal deliveryFee;
    private BigDecimal total;
    private List<ItemSummary> items;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class ItemSummary {
        private String productId;
        private String productName;
        private BigDecimal unitPrice;
        private Integer quantity;
        private String imageUrl;
    }

}
