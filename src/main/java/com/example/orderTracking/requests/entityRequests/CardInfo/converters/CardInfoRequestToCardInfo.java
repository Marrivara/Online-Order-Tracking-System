package com.example.orderTracking.requests.entityRequests.CardInfo.converters;

import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.requests.entityRequests.CardInfo.CardInfoRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CardInfoRequestToCardInfo {
    public static CardInfo convert(CardInfoRequest cardInfoRequest) {
        //convert string to date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date;
        try {
            date = dateFormat.parse(cardInfoRequest.getExpirationDate());
        } catch (Exception e) {
            date = new Date();
        }

        return CardInfo.builder()
                .cardNumber(cardInfoRequest.getCardNumber())
                .expirationDate(date)
                .cvv(cardInfoRequest.getCvv())
                .build();
    }
}
