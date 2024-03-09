package com.example.orderTracking.model.entities;

import com.example.orderTracking.enums.OrderStatus;
import com.example.orderTracking.model.baseModel.BaseModel;
import com.example.orderTracking.model.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double totalPrice;
    private OrderStatus status;
}
