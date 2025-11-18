package com.Tanatswa.Lujo_Boutique.Services;

import com.Tanatswa.Lujo_Boutique.DTO.CheckOutRequest;
import com.Tanatswa.Lujo_Boutique.DTO.CheckoutResponse;
import com.Tanatswa.Lujo_Boutique.Domain.*;
import com.Tanatswa.Lujo_Boutique.Mapper.OrderMapper;
import com.Tanatswa.Lujo_Boutique.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {

    private final CartRepository cartRepo;
    private final CustomerOrderRepository orderRepo;
    private final ProductRepository productRepo;

    @Autowired
    public CheckoutService(CartRepository cartRepo,
                           CustomerOrderRepository orderRepo,
                           ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public CheckoutResponse checkout(CheckOutRequest req) {
        if (req == null || req.getCustomerId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }

        Cart cart = cartRepo.findByCustomer_CustomerId(req.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty");
        }

        // Compute cart total
        BigDecimal cartTotal = cart.getItems().stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Determine delivery fee
        BigDecimal deliveryFee = "COLLECTION".equalsIgnoreCase(req.getDeliveryOption())
                ? BigDecimal.valueOf(60)
                : BigDecimal.valueOf(150);

        BigDecimal total = cartTotal.add(deliveryFee);

        // Simulate mock payment success
        boolean paymentSuccess = mockPayment(req.getPaymentMethod(), total);

        if (!paymentSuccess) {
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Payment failed (mock)");
        }

        // Create order and order items
        CustomerOrder order = new CustomerOrder();
        order.setCustomer(cart.getCustomer());
        order.setOrderStatus(OrderStatus.PAID);
        order.setTotal(total);
        order.setAddress(req.getAddress());
        order.setPaymentMethod(req.getPaymentMethod());
        order.setDeliveryOption(req.getDeliveryOption());

        LocalDateTime now = LocalDateTime.now();
        order.setCreatedAt(now);   // Set createdAt

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setUnitPrice(ci.getPrice());
            oi.setQuantity(ci.getQuantity());
            orderItems.add(oi);
        }
        order.setOrderItems(orderItems);

        // Save the order
        CustomerOrder saved = orderRepo.save(order);

        // Clear cart
        cart.getItems().clear();
        cartRepo.save(cart);

        // Map to CheckoutResponse
        return OrderMapper.toCheckoutResponse(saved, cartTotal, deliveryFee);
    }

    private boolean mockPayment(String method, BigDecimal amount) {
        // Simple mock: accept only "MOCK_PAYMENT"
        return "MOCK_PAYMENT".equalsIgnoreCase(method);
    }
}
