package com.Tanatswa.Lujo_Boutique.Repository;

import com.Tanatswa.Lujo_Boutique.Domain.SubCategory;
import com.Tanatswa.Lujo_Boutique.Domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    List<SubCategory> findByCategory(Category category);
}

