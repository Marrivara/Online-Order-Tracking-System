package com.example.orderTracking.responses.entityResponses.CardInfo.converters;


import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.responses.entityResponses.CardInfo.CardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.order.UserOrderResponse;
import com.example.orderTracking.responses.nestedResponses.product.converters.ProductToOrderProductResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CardInfoToCardInfoResponse {

    public static CardInfoResponse convert(CardInfo cardInfo) {
        return CardInfoResponse.builder()
                .id(cardInfo.getId())
                .cardNumber(cardInfo.getCardNumber())
                .expirationDate(cardInfo.getExpirationDate())
                .cvv(cardInfo.getCvv())
                .createdAt(cardInfo.getCreatedAt())
                .deletedAt(cardInfo.getDeletedAt())
                .build();
    }

    public static List<CardInfoResponse> convertMultiple(List<CardInfo> cardInfos) {
            return Optional.ofNullable(cardInfos).orElse(Collections.emptyList()).stream()
                    .map(CardInfoToCardInfoResponse::convert)
                    .toList();
    }
}