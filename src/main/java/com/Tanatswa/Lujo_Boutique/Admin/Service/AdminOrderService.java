package com.Tanatswa.Lujo_Boutique.Admin.Service;

import com.Tanatswa.Lujo_Boutique.Domain.CustomerOrder;
import com.Tanatswa.Lujo_Boutique.Domain.OrderStatus;
import com.Tanatswa.Lujo_Boutique.Repository.CustomerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final CustomerOrderRepository orderRepository;

    // 🔹 Get all orders (with optional filter)
    public List<CustomerOrder> getAllOrders(String filter) {
        LocalDateTime startDate = getStartDate(filter);
        if (startDate != null) {
            return orderRepository.findOrdersFromDate(startDate);
        }
        return orderRepository.findAll();
    }

    // 🔹 Update order status
    public void updateOrderStatus(Integer orderId, String status) {
        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        orderRepository.save(order);
    }

    // 🔹 Helper method to calculate start date based on filter
    private LocalDateTime getStartDate(String filter) {
        if (filter == null) return null;

        return switch (filter.toLowerCase()) {
            case "today" -> LocalDate.now().atStartOfDay();
            case "weekly" -> LocalDate.now().minusWeeks(1).atStartOfDay();
            case "monthly" -> LocalDate.now().minusMonths(1).atStartOfDay();
            default -> null;
        };
    }
}
