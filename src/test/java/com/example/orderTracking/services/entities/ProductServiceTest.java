package com.example.orderTracking.services.entities;

import com.example.orderTracking.enums.ProductCategory;
import com.example.orderTracking.exceptions.runtimeExceptions.notFoundException.ProductNotFoundException;
import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.repositories.ProductRepository;
import com.example.orderTracking.requests.entityRequests.Product.ProductRequest;
import com.example.orderTracking.requests.entityRequests.Product.converters.ProductRequestToProduct;
import com.example.orderTracking.responses.entityResponses.Product.ProductResponse;
import com.example.orderTracking.responses.entityResponses.Product.converters.ProductToProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Optional;

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
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(1);

        // Assert
        assertEquals(product, result);
    }
    @Test
    public void testGetProductById_whenProductIdDoesNotExists_shouldThrowProductNotFoundException() {
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class , () -> productService.getProductById(1));
    }
    @Test
    public void testGetProductResponseById_whenProductIdExists_shouldReturnProductResponse() {
        // Arrange
        Product product = new Product("Test", 10.0, "Test", 10, ProductCategory.ELECTRONICS);
        ProductResponse productResponse = new ProductResponse(1, "Test", 10.0, "Test", 10, ProductCategory.ELECTRONICS, null,null);

        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
        try(MockedStatic<ProductToProductResponse> converter = Mockito.mockStatic(ProductToProductResponse.class)) {
            converter.when(() -> ProductToProductResponse.convert(product)).thenReturn(productResponse);
            // Act
            ProductResponse result = productService.getProductResponseById(1);

            // Assert
            assertEquals(productResponse, result);
        }
    }
    @Test
    public void testGetProductResponseById_whenProductIdDoesNotExists_shouldThrowProductNotFoundException() {
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class , () -> productService.getProductResponseById(1));
    }

    @Test
    public void testCreateProductType_whenProductRequestIsValid_shouldReturnProductResponse() {
        // Arrange
        Product product = new Product("Test", 10.0, "Test", 10, ProductCategory.ELECTRONICS);
        ProductResponse productResponse = new ProductResponse(1, "Test", 10.0, "Test", 10, ProductCategory.ELECTRONICS, null,null);
        ProductRequest productRequest = new ProductRequest("Test", 10.0, "Test", 10, ProductCategory.ELECTRONICS);

        Mockito.when(productRepository.save(product)).thenReturn(product);
        try(MockedStatic<ProductToProductResponse> converter = Mockito.mockStatic(ProductToProductResponse.class)) {
            converter.when(() -> ProductToProductResponse.convert(product)).thenReturn(productResponse);
            try(MockedStatic<ProductRequestToProduct> requestConverter = Mockito.mockStatic(ProductRequestToProduct.class)) {
                requestConverter.when(() -> ProductRequestToProduct.convert(productRequest)).thenReturn(product);


                // Act
                ProductResponse result = productService.createProductType(productRequest);

                // Assert
                assertEquals(productResponse, result);
            }
        }
    }

}