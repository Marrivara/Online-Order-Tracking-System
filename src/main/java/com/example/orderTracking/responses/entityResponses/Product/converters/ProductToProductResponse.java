package com.example.orderTracking.responses.entityResponses.Product.converters;

import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.responses.entityResponses.CardInfo.converters.CardInfoToCardInfoResponse;
import com.example.orderTracking.responses.entityResponses.Product.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductToProductResponse {

    public static ProductResponse convert (Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .deletedAt(product.getDeletedAt())
                .build();
    }

    public static List<ProductResponse> convertMultiple(List<Product> products) {
        return Optional.ofNullable(products).orElse(Collections.emptyList()).stream()
                .map(ProductToProductResponse::convert)
                .toList();
    }
}
