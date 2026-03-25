package com.traning.ecommerce.Repositories;

import com.traning.ecommerce.DTOs.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

//Day 10: Implemented Many-to-Many relationships with Tags
public interface TagsRepository extends JpaRepository<Tags, Integer> {
}
