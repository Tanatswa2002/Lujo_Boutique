package com.Tanatswa.Lujo_Boutique.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer categoryId;
    private String name;
    private String imageUrl;
    private Integer itemCount;
    private List<SubCategoryDTO> subCategories;
}
