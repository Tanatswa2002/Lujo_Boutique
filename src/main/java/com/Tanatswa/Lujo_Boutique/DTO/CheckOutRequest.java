package com.Tanatswa.Lujo_Boutique.DTO;

import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CheckOutRequest {
    private Integer customerId;
    private String deliveryOption;
    private String address;
    private String paymentMethod;
}
