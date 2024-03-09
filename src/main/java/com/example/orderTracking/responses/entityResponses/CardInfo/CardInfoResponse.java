package com.example.orderTracking.responses.entityResponses.CardInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardInfoResponse {
    private Integer id;
    private String cardNumber;
    private Date expirationDate;
    private String cvv;
    private Date createdAt;
    private Date deletedAt;
}
