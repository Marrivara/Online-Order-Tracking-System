package com.example.orderTracking.services.Interfaces;

import com.example.orderTracking.responses.entityResponses.CardInfo.CardInfoResponse;

import java.util.List;

public interface CardInfoServiceInterface {
    List<CardInfoResponse> getAllCardInfos();

    CardInfoResponse deleteCardInfo(Integer id);
}
