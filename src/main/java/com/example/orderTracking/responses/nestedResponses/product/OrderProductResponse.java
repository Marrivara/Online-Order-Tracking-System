package com.example.orderTracking.responses.nestedResponses.product;

import com.example.orderTracking.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private ProductCategory category;
    private Date createdAt;
    private Date deletedAt;
}
