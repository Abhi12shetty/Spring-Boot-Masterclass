package com.traning.ecommerce.Services;

import com.traning.ecommerce.DTOs.Product;
import com.traning.ecommerce.Repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Day 12: Architecture & The Service Layer
@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsService() {
        System.out.println("==========getProductsService=============");
        return productRepository.findAll();
    }

    public Product getProductByIdService(@PathVariable Integer id) {
        System.out.println("==========getProductByIdService=============");
        try {
            return productRepository.findById(id).get();
        }  catch (Exception e) {
            return null;
        }
    }

    public List<Product> addProductService(@Valid @RequestBody Product newproduct) {

        System.out.println("==========addProductService=============");

        if(getProductByIdService(newproduct.getId())==null){
            productRepository.save(newproduct);
        }

        return productRepository.findAll();
    }

    public List<Product> deleteProductByIdService(@PathVariable int id) {

        System.out.println("==========deleteProductByIdService=============");

        productRepository.deleteById(id);

        return productRepository.findAll();
    }

    public List<Product> getPriceGreaterThanService(@RequestParam double minPrice) {

        System.out.println("==========getPriceGreaterThanService : "+minPrice);

        return productRepository.findByPriceGreaterThan(minPrice);

    }
    public List<Product> findByNameContainingService(@PathVariable String keyword) {

        System.out.println("==========findByNameContainingService : "+keyword);

        return productRepository.findByNameContaining(keyword);

    }

    public Page<Product> getProductPaginatedService(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        System.out.println("==========getProductPaginatedService========== ");

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return productRepository.findAll(pageable);
    }
}
