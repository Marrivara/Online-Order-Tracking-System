package com.example.orderTracking.requests.entityRequests.Order;

import com.example.orderTracking.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeOrderRequest {
    private Integer orderId;
    private OrderStatus status;
}
