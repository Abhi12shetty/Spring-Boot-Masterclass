package com.traning.ecommerce.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//DAY-5: Spring Boot Data JPA and Database Integration -Installation of MySQL and DBeaver
//DAY-6: Spring Boot Data JPA and Database Integration - Dependencies and usage of application.properties file.
@Entity
@Table(name = "products")

public class Product {

    //DAY-6: Spring Boot Data JPA and Database Integration - @Id @GeneratedValue
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Day-3: Spring Bean Validation
    @NotBlank(message = "Product name is required")
    private String name;

    @Min(1)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("products")
    private Category category;

    //Day 10: Implemented Many-to-Many relationships with Tags
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnoreProperties("products")
    private List<Tags> tags;
}
