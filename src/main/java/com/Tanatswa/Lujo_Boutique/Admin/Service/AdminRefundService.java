package com.Tanatswa.Lujo_Boutique.Admin.Service;

import com.Tanatswa.Lujo_Boutique.Admin.DTO.RefundDTO;
import com.Tanatswa.Lujo_Boutique.Admin.DTO.AdminRefundUpdateDTO;
import com.Tanatswa.Lujo_Boutique.Admin.DTO.RefundItemDTO;
import com.Tanatswa.Lujo_Boutique.Domain.Refund;
import com.Tanatswa.Lujo_Boutique.Admin.Repositories.AdminRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRefundService {

    @Autowired
    private AdminRefundRepository refundRepository;

    // Get all refunds
    public List<RefundDTO> getAllRefunds() {
        return refundRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get pending refunds only
    public List<RefundDTO> getPendingRefunds() {
        return refundRepository.findByStatus("PENDING")
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Update refund status
    public RefundDTO updateRefundStatus(Integer refundId, AdminRefundUpdateDTO dto) {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new RuntimeException("Refund not found"));
        refund.setStatus(dto.getStatus());
        Refund saved = refundRepository.save(refund);
        return mapToDTO(saved);
    }

    // Map entity to DTO
    private RefundDTO mapToDTO(Refund refund) {
        RefundDTO dto = new RefundDTO();
        dto.setRefundId(refund.getRefundId());
        dto.setAmount(refund.getAmount());
        dto.setReason(refund.getReason());
        dto.setStatus(refund.getStatus());
        dto.setCreatedAt(refund.getCreatedAt());
        dto.setCustomerId(refund.getCustomer() != null ? refund.getCustomer().getCustomerId() : null);
        dto.setOrderId(refund.getOrder() != null ? refund.getOrder().getOrderId() : null);

        if (refund.getRefundItems() != null) {
            dto.setRefundItems(refund.getRefundItems().stream().map(item -> {
                RefundItemDTO itemDTO = new RefundItemDTO();
                itemDTO.setRefundItemId(item.getRefundItemId().intValue());
                itemDTO.setProductName(item.getProduct() != null ? item.getProduct().getProductName() : null);
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPrice(item.getPrice());
                return itemDTO;
            }).collect(Collectors.toList()));
        }

        return dto;
    }
}
