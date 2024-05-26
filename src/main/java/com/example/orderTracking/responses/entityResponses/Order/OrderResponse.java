package com.example.orderTracking.responses.entityResponses.Order;

import com.example.orderTracking.enums.OrderStatus;
import com.example.orderTracking.responses.nestedResponses.product.OrderProductResponse;
import com.example.orderTracking.responses.nestedResponses.user.OrderUserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private OrderUserResponse user;
    private OrderProductResponse product;
    private Integer quantity;
    private Double totalPrice;
    private OrderStatus status;

    private Date createdAt;
    private Date deletedAt;

}
