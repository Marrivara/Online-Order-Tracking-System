package com.example.orderTracking.services.users;

import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.repositories.UserRepository;
import com.example.orderTracking.responses.entityResponses.User.UserResponse;
import com.example.orderTracking.responses.entityResponses.User.converters.UserToUserResponse;
import com.example.orderTracking.responses.nestedResponses.cardInfo.UserCardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.cardInfo.converters.CardInfoToUserCardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.order.UserOrderResponse;
import com.example.orderTracking.responses.nestedResponses.order.converters.OrderToUserOrderResponse;
import com.example.orderTracking.services.Interfaces.UserServiceInterface;
import com.example.orderTracking.services.entities.CardInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {



    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public UserResponse getUserResponseById(Integer id) {
        User user = getUserById(id);
        UserCardInfoResponse cardInfo = CardInfoToUserCardInfoResponse.convert(user.getCardInfo());
        return UserToUserResponse.convert(user,
                cardInfo,
                getUserOrderResponsesFromUser(user));
    }

    private List<UserOrderResponse> getUserOrderResponsesFromUser(User user) {
        return OrderToUserOrderResponse.convertMultiple(user.getOrders());
    }

    public void setCardInfo(User user, CardInfo cardInfo) {
        user.setCardInfo(cardInfo);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return UserToUserResponse.convertMultiple(userRepository.findAll());
    }
}