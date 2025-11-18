package com.Tanatswa.Lujo_Boutique.Services;

import com.Tanatswa.Lujo_Boutique.DTO.*;
import com.Tanatswa.Lujo_Boutique.Domain.Cart;
import com.Tanatswa.Lujo_Boutique.Domain.CartItem;
import com.Tanatswa.Lujo_Boutique.Domain.Product;
import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import com.Tanatswa.Lujo_Boutique.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    // Add product to cart
    @Transactional
    public CartDTO addToCart(Integer customerId, AddToCartRequest request) {
        // Get or create cart for customer
        Cart cart = cartRepository.findByCustomer_CustomerId(customerId)
                .orElseGet(() -> createNewCart(customerId));

        // Get product
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check requested quantity against stock
        if (request.getQuantity() > product.getQuantity()) {
            throw new RuntimeException("Requested quantity exceeds available stock");
        }

        // Check if product already in cart
        CartItem cartItem = cartItemRepository
                .findByCartCartIdAndProduct_ProductId(cart.getCartId(), request.getProductId())
                .orElse(null);

        if (cartItem != null) {
            // Ensure cumulative quantity does not exceed stock
            int newQuantity = cartItem.getQuantity() + request.getQuantity();
            if (newQuantity > product.getQuantity()) {
                throw new RuntimeException("Total quantity in cart exceeds available stock");
            }

            // Update quantity and price
            cartItem.setQuantity(newQuantity);
            cartItem.setPrice(product.getPrice());
        } else {
            // Create new cart item with correct price
            cartItem = CartItem.builder()
                    .product(product)
                    .price(product.getPrice())
                    .quantity(request.getQuantity())
                    .productImage(product.getImage())
                    .build();
            cart.addItem(cartItem);
        }

        // Update cart total
        updateCartTotal(cart);

        // Save and return DTO
        Cart savedCart = cartRepository.save(cart);
        return convertToDTO(savedCart);
    }

    // Remove product from cart
    @Transactional
    public CartDTO removeFromCart(Integer customerId, String productId, int quantityToRemove) {

        Cart cart = cartRepository.findByCustomer_CustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository
                .findByCartCartIdAndProduct_ProductId(cart.getCartId(), productId)
                .orElseThrow(() -> new RuntimeException("Item not in cart"));



        int remainingQuantity = cartItem.getQuantity() - quantityToRemove;

        if (remainingQuantity > 0) {
            cartItem.setQuantity(remainingQuantity);
            cartItemRepository.save(cartItem);
        } else {
            cart.removeItem(cartItem);
            cartItemRepository.delete(cartItem);
        }

        updateCartTotal(cart);
        Cart savedCart = cartRepository.save(cart);
        return convertToDTO(savedCart);
    }


    // View cart
    @Transactional(readOnly = true)
    public CartDTO viewCart(Integer customerId) {
        Cart cart = cartRepository.findByCustomer_CustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return convertToDTO(cart);
    }

    // Create a new cart
    private Cart createNewCart(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return Cart.builder()
                .customer(customer)
                .cartTotal(BigDecimal.ZERO)
                .build();
    }

    // Update cart total
    private void updateCartTotal(Cart cart) {
        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setCartTotal(total);
    }

    // Convert Cart to CartDTO
    private CartDTO convertToDTO(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> CartItemDTO.builder()
                        .cartItemId(item.getCartItemId())
                        .productId(item.getProduct() != null ? item.getProduct().getProductId() : null)
                        .productDescription(item.getProduct() != null ? item.getProduct().getProductDescription() : "")
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .subtotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .productImage(item.getProductImage())
                        .build())
                .collect(Collectors.toList());

        return CartDTO.builder()
                .cartId(cart.getCartId())
                .createdAt(cart.getCreatedAt())
                .total(cart.getCartTotal())
                .customerId(cart.getCustomer() != null ? cart.getCustomer().getCustomerId() : null)
                .items(itemDTOs)
                .build();
    }
}
