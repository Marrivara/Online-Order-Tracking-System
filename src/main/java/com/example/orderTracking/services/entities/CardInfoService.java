package com.example.orderTracking.services.entities;

import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.repositories.CardInfoRepository;
import com.example.orderTracking.requests.entityRequests.CardInfo.CardInfoRequest;
import com.example.orderTracking.requests.entityRequests.CardInfo.converters.CardInfoRequestToCardInfo;
import com.example.orderTracking.responses.entityResponses.CardInfo.CardInfoResponse;
import com.example.orderTracking.responses.entityResponses.CardInfo.converters.CardInfoToCardInfoResponse;
import com.example.orderTracking.services.Interfaces.CardInfoServiceInterface;
import com.example.orderTracking.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardInfoService implements CardInfoServiceInterface {

    private final UserService userService;
    private final CardInfoRepository cardInfoRepository;

    @Autowired
    public CardInfoService(@Lazy UserService userService, CardInfoRepository cardInfoRepository) {
        this.userService = userService;
        this.cardInfoRepository = cardInfoRepository;
    }

    public CardInfoResponse createCardInfo(CardInfoRequest cardInfoRequest) {
        User user = userService.getUserById(cardInfoRequest.getUserId());
        CardInfo cardInfo = CardInfoRequestToCardInfo.convert(cardInfoRequest);
        CardInfo newCard = cardInfoRepository.save(cardInfo);
        try{
            userService.setCardInfo(user, cardInfo);
        }catch (Exception e){
            cardInfoRepository.delete(newCard);
            throw new RuntimeException("Error while setting card info to user");
        }
        return CardInfoToCardInfoResponse.convert(newCard);
    }

    public CardInfo getCardInfoById(Integer id){
        return cardInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Card info not found"));
    }

    public CardInfoResponse getCardInfoResponseById(Integer id){
        return CardInfoToCardInfoResponse.convert(getCardInfoById(id));
    }

    @Override
    public List<CardInfoResponse> getAllCardInfos() {
        return CardInfoToCardInfoResponse.convertMultiple(cardInfoRepository.findAll());
    }

    @Override
    public CardInfoResponse deleteCardInfo(Integer id) {
        CardInfo cardInfo = getCardInfoById(id);
        User user = userService.getUserById(cardInfo.getUser().getId());
        user.setCardInfo(null);
        cardInfoRepository.delete(cardInfo);
        return CardInfoToCardInfoResponse.convert(cardInfo);
    }

}
