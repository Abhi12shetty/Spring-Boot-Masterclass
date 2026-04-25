package com.traning.ecommerce.Controllers;

import com.traning.ecommerce.DTOs.Product;
import com.traning.ecommerce.Payloads.ProductDTO;
import com.traning.ecommerce.Repositories.ProductRepository;
import com.traning.ecommerce.Services.ExternalApiService;
import com.traning.ecommerce.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//DAY2- Spring Boot MVC and RESTful APIs GET, POST
@RestController
@RequestMapping("/api/products")
public class ProductController {


    //DAY-7: Spring Data JPA Repositories
    /*@Autowired
    private ProductRepository productRepository;*/ //--commented due to - Day 12: Architecture & The Service Layer

    //Day 12: Architecture & The Service Layer
    @Autowired
    private ProductService  productService;

    //Day 29: Calling Third-Party APIs (RestTemplate)
    // 1. Inject the service
    private final ExternalApiService externalApiService;
    public ProductController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    /*private List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(101, "Puma", 100),
        new Product(102, "Nike", 200)));*/ // Commented due to no constructor after Database relationships


    @GetMapping
    public List<Product> getProducts() {

        System.out.println("========== getProducts =============");
        //return products;

        //DAY-7: Spring Data JPA Repositories
        /*return productRepository.findAll();*/ //--commented due to - Day 12: Architecture & The Service Layer

        //Day 12: Architecture & The Service Layer - productService (all methods calling service endpoint in this controller due to DAY-12
        return productService.getProductsService();
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
    public  ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        System.out.println("==========getProductById=============");
        /*Product item = products.stream()
                .filter(product-> product.getId()==id)
                .findFirst().orElse(null);
        return item;*/

        ProductDTO dto = null;
        /*try {//Commented try - catch block and if null else block bcz - Day 22: Global Exception Handling*/
            /*return productRepository.findById(id).get();*/ //--commented due to - Day 12: Architecture & The Service Layer
            //Day 12: Architecture & The Service Layer - productService
            dto = productService.getProductByIdService(id);
        /*}  catch (Exception e) {
            return null;
        }
        if(dto!=null){
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //Day-3: Spring Bean Validation
    @PostMapping
    public ResponseEntity<List<Product>> addProduct(@Valid @RequestBody Product newproduct) {

        System.out.println("==========addProduct=============");
        /*if(!products.contains(newproduct)) {
            //products.add(newproduct);
        }*/
        if (newproduct.getId() == null) {
            productService.addProductService(newproduct);
        }
        if(getProductById(newproduct.getId())==null){
            //productRepository.save(newproduct);
            productService.addProductService(newproduct);
        }

        List<Product> allProducts =  productService.getProductsService();
        return ResponseEntity.status(HttpStatus.CREATED).body(allProducts);

    }

    @GetMapping("/delete/{id}")
    public List<Product> deleteProductById(@PathVariable int id) {

        System.out.println("==========deleteProductById=============");

        //productRepository.deleteById(id);
        productService.deleteProductByIdService(id);

        //return productRepository.findAll();
        return productService.getProductsService();
    }

    //DAY-8: Update the Repository - usage
    @GetMapping("/expensive")
    public List<Product> getPriceGreaterThan(@RequestParam double minPrice) {

        System.out.println("==========getPriceGreaterThan : "+minPrice);

        //return productRepository.findByPriceGreaterThan(minPrice);
        return productService.getPriceGreaterThanService(minPrice);

    }
    @GetMapping("/findByNameContaining/{keyword}")
    public List<Product> findByNameContaining(@PathVariable String keyword) {

        System.out.println("==========findByNameContaining : "+keyword);

        //return productRepository.findByNameContaining(keyword);
        return productService.findByNameContainingService(keyword);

    }

    //Day 11: Pagination & Sorting!
    @GetMapping("/page")
    public Page<Product> getProductPaginated(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "id") String sortBy
    ){
        System.out.println("==========getProductPaginated========== ");

        /*Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return productRepository.findAll(pageable);*/
        return productService.getProductPaginatedService(pageNumber, pageSize, sortBy);
    }

    //Day 27: Database Transactions (@Transactional)
    @GetMapping("/test-crash")
    public ResponseEntity<String> testTransactionCrash() {
        String result = productService.addTwoProductsWithCrash();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Day 29: Calling Third-Party APIs (RestTemplate)
    // (Add externalApiService to your ProductController constructor!)

    // 2. Add the endpoint
    @GetMapping("/external")
    public ResponseEntity<Object> getExternalProduct() {
        Object externalData = externalApiService.fetchExternalProduct();
        return new ResponseEntity<>(externalData, HttpStatus.OK);
    }
}
