package com.example.orderTracking.services.entities;

import com.example.orderTracking.enums.Role;
import com.example.orderTracking.exceptions.runtimeExceptions.authorizationExceptions.UnauthorizedException;
import com.example.orderTracking.exceptions.runtimeExceptions.internalServerErrors.SetCardInfoToUserException;
import com.example.orderTracking.exceptions.runtimeExceptions.notFoundException.CardInfoNotFoundException;
import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.repositories.CardInfoRepository;
import com.example.orderTracking.requests.entityRequests.CardInfo.CardInfoRequest;
import com.example.orderTracking.requests.entityRequests.CardInfo.converters.CardInfoRequestToCardInfo;
import com.example.orderTracking.responses.entityResponses.CardInfo.CardInfoResponse;
import com.example.orderTracking.responses.entityResponses.CardInfo.converters.CardInfoToCardInfoResponse;
import com.example.orderTracking.services.Interfaces.CardInfoServiceInterface;
import com.example.orderTracking.services.jwt.JwtService;
import com.example.orderTracking.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardInfoService implements CardInfoServiceInterface {

    private final UserService userService;
    private final CardInfoRepository cardInfoRepository;
    private final JwtService jwtService;

    @Autowired
    public CardInfoService(@Lazy UserService userService, CardInfoRepository cardInfoRepository, JwtService jwtService) {
        this.userService = userService;
        this.cardInfoRepository = cardInfoRepository;
        this.jwtService = jwtService;
    }

    public CardInfoResponse createCardInfo(CardInfoRequest cardInfoRequest) {
        User user = userService.getUserById(cardInfoRequest.getUserId());
        CardInfo cardInfo = CardInfoRequestToCardInfo.convert(cardInfoRequest);
        CardInfo newCard = cardInfoRepository.save(cardInfo);
        try {
            userService.setCardInfo(user, cardInfo);
        } catch (Exception e) {
            cardInfoRepository.delete(newCard);
            throw new SetCardInfoToUserException("Error while setting card info to user. Card info is deleted.");
        }
        return CardInfoToCardInfoResponse.convert(newCard);
    }

    public CardInfo getCardInfoById(Integer id) {
        return cardInfoRepository.findById(id).orElseThrow(() -> new CardInfoNotFoundException("Card info not found"));
    }

    public CardInfoResponse getCardInfoResponseById(Integer id, String token) {
        String userEmail = jwtService.extractUserEmail(token.substring(7));
        User user = userService.getUserByEmail(userEmail);
        if (user.getRole() != Role.ROLE_MANAGER) {
            if (!user.getCardInfo().getId().equals(id)) {
                throw new UnauthorizedException("You can only get your own card info");
            }
        }
        return CardInfoToCardInfoResponse.convert(getCardInfoById(id));
    }

    @Override
    public List<CardInfoResponse> getAllCardInfos() {
        return CardInfoToCardInfoResponse.convertMultiple(cardInfoRepository.findAll());
    }

    @Override
    public CardInfoResponse deleteCardInfo(Integer id, String token) {
        String userEmail = jwtService.extractUserEmail(token.substring(7));
        User user = userService.getUserByEmail(userEmail);//User that is trying to delete the card info
        if (user.getRole() != Role.ROLE_MANAGER) {
            if (user.getCardInfo().getId().equals(id)) {
                throw new UnauthorizedException("You can only delete your own card info");
            }
        }
        CardInfo cardInfo = getCardInfoById(id);
        User userWithCardInfo = cardInfo.getUser();
        userWithCardInfo.setCardInfo(null);
        cardInfoRepository.delete(cardInfo);
        return CardInfoToCardInfoResponse.convert(cardInfo);
    }

}
