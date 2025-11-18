package com.Tanatswa.Lujo_Boutique.Repository;


import com.Tanatswa.Lujo_Boutique.Domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    List<Category> findByNameContainingIgnoreCase(String name);
}
