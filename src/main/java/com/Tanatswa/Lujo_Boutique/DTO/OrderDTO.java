package com.Tanatswa.Lujo_Boutique.DTO;

import lombok.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private String orderStatus;
    private BigDecimal total;
    private String deliveryOption;
    private String address;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private List<CheckoutResponse.ItemSummary> items;
}

