package com.Tanatswa.Lujo_Boutique.Repository;
import com.Tanatswa.Lujo_Boutique.Domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder_Customer_CustomerIdOrderByOrder_CreatedAtDesc(Integer customerId);


}
