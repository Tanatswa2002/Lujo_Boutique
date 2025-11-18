package com.Tanatswa.Lujo_Boutique.Repository;
import com.Tanatswa.Lujo_Boutique.Domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Integer>{
    List<CustomerOrder>findByCustomer_CustomerIdOrderByCreatedAtDesc(Integer customerId);
    List<CustomerOrder>findByCustomer_CustomerId(Integer customerId);
    List<CustomerOrder>findByCustomer_CustomerIdAndOrderStatus(Integer customerId,String orderStatus);
    @Query("SELECT o FROM CustomerOrder o WHERE o.createdAt >= :startDate")
    List<CustomerOrder> findOrdersFromDate(LocalDateTime startDate);


}
