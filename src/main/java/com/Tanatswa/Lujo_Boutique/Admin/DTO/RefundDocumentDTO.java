package com.Tanatswa.Lujo_Boutique.Admin.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundDocumentDTO {
    private Integer documentId;
    private String fileName;
    private String fileType;
}

