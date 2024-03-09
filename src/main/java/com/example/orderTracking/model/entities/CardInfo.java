package com.example.orderTracking.model.entities;


import com.example.orderTracking.model.baseModel.BaseModel;
import com.example.orderTracking.model.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "cardInfo")
public class CardInfo extends BaseModel {
    private String cardNumber;
    private Date expirationDate;
    private String cvv;

    @OneToOne(mappedBy = "cardInfo")
    @JsonIgnore
    private User user;
}