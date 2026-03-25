package com.traning.ecommerce.Controllers;

import com.traning.ecommerce.DTOs.Product;
import com.traning.ecommerce.Repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//DAY2- Spring Boot MVC and RESTful APIs GET, POST
@RestController
@RequestMapping("/api/products")
public class ProductController {


    //DAY-7: Spring Data JPA Repositories
    @Autowired
    private ProductRepository productRepository;

    /*private List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(101, "Puma", 100),
        new Product(102, "Nike", 200)));*/ // Commented due to no constructor after Database relationships


    @GetMapping
    public List<Product> getProducts() {

        System.out.println("==========getProducts=============");
        //return products;

        //DAY-7: Spring Data JPA Repositories
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
    public Product getProductById(@PathVariable Integer id) {
        System.out.println("==========getProductById=============");
        /*Product item = products.stream()
                .filter(product-> product.getId()==id)
                .findFirst().orElse(null);
        return item;*/

        try {
            return productRepository.findById(id).get();
        }  catch (Exception e) {
            return null;
        }
    }

    //Day-3: Spring Bean Validation
    @PostMapping
    public List<Product> addProduct(@Valid @RequestBody Product newproduct) {

        System.out.println("==========addProduct=============");
        /*if(!products.contains(newproduct)) {
            //products.add(newproduct);
        }*/
        if(getProductById(newproduct.getId())==null){
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

    //DAY-8: Update the Repository - usage
    @GetMapping("/expensive")
    public List<Product> getPriceGreaterThan(@RequestParam double minPrice) {

        System.out.println("==========getPriceGreaterThan : "+minPrice);

        return productRepository.findByPriceGreaterThan(minPrice);

    }
    @GetMapping("/findByNameContaining/{keyword}")
    public List<Product> findByNameContaining(@PathVariable String keyword) {

        System.out.println("==========findByNameContaining : "+keyword);

        return productRepository.findByNameContaining(keyword);

    }
}
