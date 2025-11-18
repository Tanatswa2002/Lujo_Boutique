package com.Tanatswa.Lujo_Boutique.Services;
import com.Tanatswa.Lujo_Boutique.DTO.OrderDTO;
import com.Tanatswa.Lujo_Boutique.Domain.CustomerOrder;
import com.Tanatswa.Lujo_Boutique.Mapper.OrderMapper;
import com.Tanatswa.Lujo_Boutique.Repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final CustomerOrderRepository orderRepo;

    @Autowired
    public OrderService(CustomerOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<OrderDTO> getOrdersForCustomer(Integer customerId) {
        List<CustomerOrder> orders = orderRepo.findByCustomer_CustomerIdOrderByCreatedAtDesc(customerId);
        return orders.stream().map(OrderMapper::toOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrder(Integer orderId) {
        CustomerOrder order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("order not found"));
        return OrderMapper.toOrderDTO(order);
    }
}

