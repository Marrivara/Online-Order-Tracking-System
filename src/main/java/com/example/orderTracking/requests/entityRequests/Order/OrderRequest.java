package com.example.orderTracking.requests.entityRequests.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
}
