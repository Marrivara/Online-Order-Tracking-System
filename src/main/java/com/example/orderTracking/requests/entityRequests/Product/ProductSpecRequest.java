package com.example.orderTracking.requests.entityRequests.Product;

import com.example.orderTracking.enums.ProductCategory;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecRequest {
    private String name;
    private ProductCategory category;
    private Double bottomPrice;
    private Double topPrice;
}
