package com.example.orderTracking.responses.nestedResponses.cardInfo.converters;

import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.responses.nestedResponses.cardInfo.UserCardInfoResponse;
import org.springframework.stereotype.Component;

@Component
public class CardInfoToUserCardInfoResponse {

    public static UserCardInfoResponse convert(CardInfo cardInfo) {
        if (cardInfo == null) {
            return null;
        }
        return UserCardInfoResponse.builder()
                .id(cardInfo.getId())
                .cardNumber(cardInfo.getCardNumber())
                .expirationDate(cardInfo.getExpirationDate())
                .createdAt(cardInfo.getCreatedAt())
                .deletedAt(cardInfo.getDeletedAt())
                .build();
    }
}
