package com.traning.ecommerce.Controllers;

import com.traning.ecommerce.DTOs.Category;
import com.traning.ecommerce.DTOs.Product;
import com.traning.ecommerce.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//DAY-9: Saving Relational Data
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository  categoryRepository;

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category) {
        System.out.print("=======addCategory========");

        if(category.getProducts()!=null){
            for(Product product:category.getProducts()){
                product.setCategory(category);
            }
        }

        return categoryRepository.save(category);
    }

    @GetMapping("/getAllCategory")
    public List<Category> findAllCategory(){
        System.out.print("=======findAllCategory========");
        return categoryRepository.findAll();
    }

}
