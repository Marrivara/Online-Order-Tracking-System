package com.example.orderTracking.model.entities;

import com.example.orderTracking.enums.ProductCategory;
import com.example.orderTracking.model.baseModel.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseModel {
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private ProductCategory category;

}
