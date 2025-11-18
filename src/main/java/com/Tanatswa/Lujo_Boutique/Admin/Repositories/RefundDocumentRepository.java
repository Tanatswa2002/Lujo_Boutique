package com.Tanatswa.Lujo_Boutique.Admin.Repositories;
import com.Tanatswa.Lujo_Boutique.Domain.RefundDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundDocumentRepository extends JpaRepository<RefundDocument, Long> {
}

