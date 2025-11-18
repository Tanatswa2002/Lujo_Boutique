package com.Tanatswa.Lujo_Boutique.Controller;
import com.Tanatswa.Lujo_Boutique.DTO.*;
import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import com.Tanatswa.Lujo_Boutique.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{customerId}/add")
    public ResponseEntity<CartDTO> addToCart(
            @PathVariable Integer customerId,
            @RequestBody AddToCartRequest request) {
        CartDTO cart = cartService.addToCart(customerId, request);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{customerId}/remove/{productId}")
    public ResponseEntity<CartDTO> removeFromCart(
            @PathVariable Integer customerId,
            @PathVariable String productId,
            @RequestParam int quantity) { // <- quantity from request
        CartDTO cart = cartService.removeFromCart(customerId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTO> viewCart(@PathVariable Integer customerId) {
        CartDTO cart = cartService.viewCart(customerId);
        return ResponseEntity.ok(cart);
    }
}
