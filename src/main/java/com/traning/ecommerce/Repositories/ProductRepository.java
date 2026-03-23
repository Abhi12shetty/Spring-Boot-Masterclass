package com.traning.ecommerce.Repositories;

import com.traning.ecommerce.DTOs.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
