package com.Tanatswa.Lujo_Boutique.Repository;
// CartItemRepository.java


import com.Tanatswa.Lujo_Boutique.Domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartCartIdAndProduct_ProductId(Integer cartId, String productId);
}