package com.Tanatswa.Lujo_Boutique.Admin.Repositories;

import com.Tanatswa.Lujo_Boutique.Domain.RefundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundItemRepository extends JpaRepository<RefundItem, Long> {
}
