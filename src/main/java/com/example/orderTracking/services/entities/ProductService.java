package com.example.orderTracking.services.entities;

import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.repositories.ProductRepository;
import com.example.orderTracking.requests.entityRequests.Product.ProductRequest;
import com.example.orderTracking.requests.entityRequests.Product.converters.ProductRequestToProduct;
import com.example.orderTracking.responses.entityResponses.Product.ProductResponse;
import com.example.orderTracking.responses.entityResponses.Product.converters.ProductToProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse getProductResponseById(Integer id) {
        return ProductToProductResponse.convert(getProductById(id));
    }


    public ProductResponse createProductType(ProductRequest productRequest) {
        Product product = ProductRequestToProduct.convert(productRequest);
        return ProductToProductResponse.convert(productRepository.save(product));
    }

    public List<ProductResponse> getAllProductTypes() {
        return ProductToProductResponse.convertMultiple(productRepository.findAll());
    }

    public ProductResponse updateProductQuantity(Integer id, Integer quantity) {
        Product product = getProductById(id);
        product.setQuantity(quantity);
        return ProductToProductResponse.convert(productRepository.save(product));
    }
}
