package com.Tanatswa.Lujo_Boutique.Repository;

import com.Tanatswa.Lujo_Boutique.Domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductProductId(String productId);
    List<Review> findByCustomerCustomerId(Integer customerId);
}
