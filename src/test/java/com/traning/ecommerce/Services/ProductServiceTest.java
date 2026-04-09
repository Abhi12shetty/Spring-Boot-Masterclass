package com.traning.ecommerce.Services;

import com.traning.ecommerce.DTOs.Product;
import com.traning.ecommerce.Payloads.ProductDTO;
import com.traning.ecommerce.Repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;
//Day 21: Unit Testing (JUnit 5 & Mockito)!
@ExtendWith(MockitoExtension.class) // Tells JUnit to enable Mockito!
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // A fake repository

    @Mock
    private ModelMapper modelMapper; // A fake model mapper

    @InjectMocks
    private ProductService productService; // The REAL service we are testing

    // Tests go here...
    @Test
    public void testGetProductById_Success() {
        // 1. ARRANGE: Set up our fake data and rules
        Integer testId = 1;
        Product fakeProduct = new Product();
        fakeProduct.setId(testId);
        fakeProduct.setName("Test Keyboard");
        fakeProduct.setPrice(100.0);

        ProductDTO fakeDto = new ProductDTO();
        fakeDto.setId(testId);
        fakeDto.setName("Test Keyboard");
        fakeDto.setPrice(100.0);

        // Rule: When the repository is asked for ID 1, return our fakeProduct
        Mockito.when(productRepository.findById(testId)).thenReturn(Optional.of(fakeProduct));

        // Rule: When the mapper is asked to convert it, return our fakeDto
        Mockito.when(modelMapper.map(fakeProduct, ProductDTO.class)).thenReturn(fakeDto);

        // 2. ACT: Actually call the real service method
       ProductDTO result = productService.getProductByIdService(testId);

        // 3. ASSERT: Check if the method did its job correctly
        Assertions.assertNotNull(result, "The returned DTO should not be null");
        Assertions.assertEquals("Test Keyboard", result.getName(), "The name should match our fake data");
        Assertions.assertEquals(100.0, result.getPrice());
    }

    @Test
    public void testGetProductById_NotFound() {
        // ARRANGE: Rule: When repo searches for ID 99, return an empty Optional (Nothing found)
        Integer badId = 99;
        Mockito.when(productRepository.findById(badId)).thenReturn(Optional.empty());

        // ACT
        ProductDTO result = productService.getProductByIdService(badId);

        // ASSERT: Our service should safely return null
        Assertions.assertNull(result, "If product is not in DB, service should return null");
    }
}