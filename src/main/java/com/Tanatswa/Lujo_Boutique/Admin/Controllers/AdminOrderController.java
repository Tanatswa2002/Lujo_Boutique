package com.Tanatswa.Lujo_Boutique.Admin.Controllers;
import com.Tanatswa.Lujo_Boutique.Domain.CustomerOrder;
import com.Tanatswa.Lujo_Boutique.Admin.Service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    // 🔹 Fetch all orders (optional filter: today, weekly, monthly)
    @GetMapping
    public List<CustomerOrder> getAllOrders(@RequestParam(required = false) String filter) {
        return adminOrderService.getAllOrders(filter);
    }

    // 🔹 Update order status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Integer orderId,
                                                    @RequestParam String status) {
        adminOrderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("✅ Order status updated successfully");
    }
}

