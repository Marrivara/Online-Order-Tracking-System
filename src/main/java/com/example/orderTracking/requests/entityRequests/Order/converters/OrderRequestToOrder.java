package com.example.orderTracking.requests.entityRequests.Order.converters;

import com.example.orderTracking.enums.OrderStatus;
import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.model.entities.Product;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.requests.entityRequests.Order.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestToOrder {
    public static Order convert(OrderRequest orderRequest, User user, Product product) {
        return Order.builder()
                .user(user)
                .product(product)
                .quantity(orderRequest.getQuantity())
                .totalPrice(product.getPrice() * orderRequest.getQuantity())
                .status(OrderStatus.NEW)
                .build();
    }
}
