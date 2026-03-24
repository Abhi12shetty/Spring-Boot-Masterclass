package com.traning.ecommerce.Repositories;

import com.traning.ecommerce.DTOs.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//DAY-7: Spring Data JPA Repositories - JpaRepository<Entity, id>, findAll, findById, save, deleteById
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //DAY-8: Update the Repository
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByNameContaining(String keyword);
}
