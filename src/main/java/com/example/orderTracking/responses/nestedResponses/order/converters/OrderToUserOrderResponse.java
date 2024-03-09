package com.example.orderTracking.responses.nestedResponses.order.converters;

import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.responses.nestedResponses.order.UserOrderResponse;
import com.example.orderTracking.responses.nestedResponses.product.OrderProductResponse;
import com.example.orderTracking.responses.nestedResponses.product.converters.ProductToOrderProductResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class OrderToUserOrderResponse {

    public static UserOrderResponse convert(Order order, OrderProductResponse product) {
        return UserOrderResponse.builder()
                .id(order.getId())
                .product(product)
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .deletedAt(order.getDeletedAt())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static List<UserOrderResponse> convertMultiple(List<Order> orders) {
        return Optional.ofNullable(orders).orElse(Collections.emptyList()).stream()
                .map(order -> convert(order, ProductToOrderProductResponse.convert(order.getProduct())))
                .toList();
    }
}
