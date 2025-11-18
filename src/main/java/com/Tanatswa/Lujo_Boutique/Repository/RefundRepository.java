package com.Tanatswa.Lujo_Boutique.Repository;
import com.Tanatswa.Lujo_Boutique.Domain.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund,Integer> {
    List<Refund>findByCustomer_CustomerId(Integer customerId);

}
