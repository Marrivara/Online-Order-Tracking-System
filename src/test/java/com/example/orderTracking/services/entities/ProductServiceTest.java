package com.example.orderTracking.services.entities;

import com.example.orderTracking.enums.ProductCategory;
import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetProductById_whenProductIdExists_shouldReturnProduct() {
        // Arrange
        Product product = new Product("Test", 10.0, "Test", 10, ProductCategory.ELECTRONICS);
        Mockito.when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));

        // Act
        Product result = productService.getProductById(1);

        // Assert
        assertEquals(product, result);
    }

}