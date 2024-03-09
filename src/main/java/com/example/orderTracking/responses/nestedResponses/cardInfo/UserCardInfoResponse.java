package com.example.orderTracking.responses.nestedResponses.cardInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCardInfoResponse {
    private Integer id;
    private String cardNumber;
    private Date expirationDate;
    private Date createdAt;
    private Date deletedAt;
}
