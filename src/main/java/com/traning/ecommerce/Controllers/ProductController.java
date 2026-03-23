package com.traning.ecommerce.Controllers;

import com.traning.ecommerce.DTOs.Product;
import com.traning.ecommerce.Repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(101, "Puma", 100),
        new Product(102, "Nike", 200)));


    @GetMapping
    public List<Product> getProducts() {

        System.out.println("==========getProducts=============");
        //return products;
        return productRepository.findAll();
    }

    /*@GetMapping("/ideal")
    public List<Product> getIdealProducts() {
        List<Product> products = Arrays.asList(
                new Product(101, "Puma-Ideal", 100),
                new Product(102, "Nike-Ideal", 200),
                new Product(103, "Jordan-Ideal", 300),
                new Product(104, "Angel-Ideal", 400),
                new Product(105, "BMW-Ideal", 500),
                new Product(105, "Wildcraft-Ideal", 600)

        );
        return products;
    }*/

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        System.out.println("==========getProductById=============");
        /*Product item = products.stream()
                .filter(product-> product.getId()==id)
                .findFirst().orElse(null);
        return item;*/

        return productRepository.findById(id).get();
    }

    @PostMapping
    public List<Product> addProduct(@Valid @RequestBody Product newproduct) {

        System.out.println("==========addProduct=============");
        if(!products.contains(newproduct)) {
            //products.add(newproduct);
            productRepository.save(newproduct);
        }

        return productRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    public List<Product> deleteProductById(@PathVariable int id) {

        System.out.println("==========deleteProductById=============");

        productRepository.deleteById(id);

        return productRepository.findAll();
    }
}
