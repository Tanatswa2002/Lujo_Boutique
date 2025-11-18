package com.Tanatswa.Lujo_Boutique.Admin.Repositories;

import com.Tanatswa.Lujo_Boutique.Domain.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRefundRepository extends JpaRepository<Refund, Integer> {
    List<Refund> findByStatus(String status);
}
