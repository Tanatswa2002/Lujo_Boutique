package com.Tanatswa.Lujo_Boutique.Controller;
import com.Tanatswa.Lujo_Boutique.DTO.CheckOutRequest;
import com.Tanatswa.Lujo_Boutique.DTO.CheckoutResponse;
import com.Tanatswa.Lujo_Boutique.DTO.OrderDTO;
import com.Tanatswa.Lujo_Boutique.Services.CheckoutService;
import com.Tanatswa.Lujo_Boutique.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final OrderService orderService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService, OrderService orderService) {
        this.checkoutService = checkoutService;
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckOutRequest req) {
        CheckoutResponse resp = checkoutService.checkout(req);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Integer customerId) {
        List<OrderDTO> dtos = orderService.getOrdersForCustomer(customerId);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Integer orderId) {
        OrderDTO dto = orderService.getOrder(orderId);
        return ResponseEntity.ok(dto);
    }
}
