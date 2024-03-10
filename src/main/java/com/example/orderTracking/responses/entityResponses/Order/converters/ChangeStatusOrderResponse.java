package com.example.orderTracking.responses.entityResponses.Order.converters;

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
public class ChangeStatusOrderResponse {
    private Integer id;
    private String userName;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
    private OrderStatus status;
}
