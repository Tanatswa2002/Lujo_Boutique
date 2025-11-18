package com.Tanatswa.Lujo_Boutique.Admin.DTO;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundDTO {
    private Integer refundId;
    private BigDecimal amount;
    private String reason;
    private String status;
    private LocalDateTime createdAt;
    private Integer adminId;
    private Integer customerId;
    private Integer orderId;
    private List<RefundItemDTO> refundItems;
    private List<RefundDocumentDTO> refundDocuments;
}

