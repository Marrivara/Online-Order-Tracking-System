package com.example.orderTracking.requests.entityRequests.Product.converters;

import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.requests.entityRequests.Product.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestToProduct {
    public static Product convert (ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .category(productRequest.getCategory())
                .build();
    }
}
