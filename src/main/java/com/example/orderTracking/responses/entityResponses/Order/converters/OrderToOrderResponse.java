package com.example.orderTracking.responses.entityResponses.Order.converters;

import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.responses.entityResponses.Order.OrderResponse;
import com.example.orderTracking.responses.nestedResponses.product.OrderProductResponse;
import com.example.orderTracking.responses.nestedResponses.product.converters.ProductToOrderProductResponse;
import com.example.orderTracking.responses.nestedResponses.user.OrderUserResponse;
import com.example.orderTracking.responses.nestedResponses.user.converters.UserToOrderUserResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class OrderToOrderResponse {
    public static OrderResponse convert(Order order, OrderUserResponse user, OrderProductResponse product) {
        return OrderResponse.builder()
                .id(order.getId())
                .user(user)
                .product(product)
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .deletedAt(order.getDeletedAt())
                .build();
    }

    public static List<OrderResponse> convertMultiple(List<Order> orders) {
        return Optional.ofNullable(orders).orElse(Collections.emptyList()).stream()
                .map(order -> convert(order,
                        UserToOrderUserResponse.convert(order.getUser()),
                        ProductToOrderProductResponse.convert(order.getProduct())))
                .toList();
    }
}
