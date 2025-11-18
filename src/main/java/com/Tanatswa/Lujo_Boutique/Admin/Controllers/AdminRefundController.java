package com.Tanatswa.Lujo_Boutique.Admin.Controllers;

import com.Tanatswa.Lujo_Boutique.Admin.DTO.RefundDTO;
import com.Tanatswa.Lujo_Boutique.Admin.DTO.AdminRefundUpdateDTO;
import com.Tanatswa.Lujo_Boutique.Admin.Service.AdminRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/refunds")
public class AdminRefundController {

    @Autowired
    private AdminRefundService refundService;

    // Get all refunds
    @GetMapping
    public List<RefundDTO> getAllRefunds() {
        return refundService.getAllRefunds();
    }

    // Get only pending refunds
    @GetMapping("/pending")
    public List<RefundDTO> getPendingRefunds() {
        return refundService.getPendingRefunds();
    }

    // Update refund status
    @PatchMapping("/{refundId}/status")
    public RefundDTO updateRefundStatus(@PathVariable Integer refundId,
                                        @RequestBody AdminRefundUpdateDTO dto) {
        return refundService.updateRefundStatus(refundId, dto);
    }
}
