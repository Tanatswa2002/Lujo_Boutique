package com.Tanatswa.Lujo_Boutique.Mapper;

import com.Tanatswa.Lujo_Boutique.DTO.CheckoutResponse;
import com.Tanatswa.Lujo_Boutique.DTO.OrderDTO;
import com.Tanatswa.Lujo_Boutique.Domain.CustomerOrder;
import com.Tanatswa.Lujo_Boutique.Domain.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {
    public static CheckoutResponse toCheckoutResponse(CustomerOrder order,
                                                      java.math.BigDecimal cartTotal,
                                                      java.math.BigDecimal deliveryFee) {
        CheckoutResponse resp = new CheckoutResponse();
        resp.setOrderId(order.getOrderId());
        resp.setOrderStatus(order.getOrderStatus().name()); // converts enum to "PENDING", "PAID", etc.
        resp.setDeliveryOption(order.getDeliveryOption());
        resp.setCartTotal(cartTotal);
        resp.setDeliveryFee(deliveryFee);
        resp.setTotal(order.getTotal());

        resp.setItems(order.getOrderItems().stream().map(oi -> {
            CheckoutResponse.ItemSummary i = new CheckoutResponse.ItemSummary();
            i.setProductId(oi.getProduct().getProductId());
            i.setProductName(oi.getProduct().getProductDescription());
            i.setUnitPrice(oi.getUnitPrice());
            i.setQuantity(oi.getQuantity());

            i.setImageUrl("/api/products/" + oi.getProduct().getProductId() + "/image");
            return i;
        }).collect(Collectors.toList()));

        return resp;
    }

    public static OrderDTO toOrderDTO(CustomerOrder order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setOrderStatus(order.getOrderStatus().name());
        dto.setTotal(order.getTotal());
        dto.setDeliveryOption(order.getDeliveryOption());
        dto.setAddress(order.getAddress());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setItems(order.getOrderItems().stream().map(oi -> {
            CheckoutResponse.ItemSummary i = new CheckoutResponse.ItemSummary();
            i.setProductId(oi.getProduct().getProductId());
            i.setProductName(oi.getProduct().getProductDescription());
            i.setUnitPrice(oi.getUnitPrice());
            i.setQuantity(oi.getQuantity());
            i.setImageUrl("/api/products/" + oi.getProduct().getProductId() + "/image");
            return i;
        }).collect(Collectors.toList()));
        return dto;
    }
}

