package com.traning.ecommerce.Repositories;

import com.traning.ecommerce.DTOs.Category;
import org.springframework.data.jpa.repository.JpaRepository;
//DAY-9: Saving Relational Data
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
