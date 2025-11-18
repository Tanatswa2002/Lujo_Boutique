package com.Tanatswa.Lujo_Boutique.Repository;
import com.Tanatswa.Lujo_Boutique.Domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByCustomer_CustomerId(Integer customerId);

}
