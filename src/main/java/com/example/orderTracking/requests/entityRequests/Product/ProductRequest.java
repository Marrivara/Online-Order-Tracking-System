package com.example.orderTracking.requests.entityRequests.Product;

import com.example.orderTracking.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private ProductCategory category;

}
