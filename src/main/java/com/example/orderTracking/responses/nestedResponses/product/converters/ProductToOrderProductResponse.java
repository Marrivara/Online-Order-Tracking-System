package com.example.orderTracking.responses.nestedResponses.product.converters;

import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.responses.nestedResponses.product.OrderProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductToOrderProductResponse {

    public static OrderProductResponse convert(Product product) {
        return OrderProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .deletedAt(product.getDeletedAt())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
