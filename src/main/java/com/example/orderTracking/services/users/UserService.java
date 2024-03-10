package com.example.orderTracking.services.users;

import com.example.orderTracking.exceptions.runtimeExceptions.notFoundException.UserNotFoundException;
import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.repositories.UserRepository;
import com.example.orderTracking.requests.authRequests.RegisterRequest;
import com.example.orderTracking.responses.entityResponses.User.UserResponse;
import com.example.orderTracking.responses.entityResponses.User.converters.UserToUserResponse;
import com.example.orderTracking.responses.nestedResponses.cardInfo.UserCardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.cardInfo.converters.CardInfoToUserCardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.order.UserOrderResponse;
import com.example.orderTracking.responses.nestedResponses.order.converters.OrderToUserOrderResponse;
import com.example.orderTracking.services.Interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {



    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    public UserResponse getUserResponseById(Integer id) {
        User user = getUserById(id);
        UserCardInfoResponse cardInfo = CardInfoToUserCardInfoResponse.convert(user.getCardInfo());
        return UserResponseConverterCaller(user, user.getCardInfo(), getUserOrderResponsesFromUser(user));
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

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException("User not found by email."));
    }

    @Override
    public UserResponse updateUser(Integer id, RegisterRequest userRequest) {
        User user = getUserById(id);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setGender(userRequest.getGender());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());
        user.setAddress(userRequest.getAddress());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRole(userRequest.getRole());
        userRepository.save(user);
        return getUserResponseById(id);
    }

    private UserResponse UserResponseConverterCaller(User user, CardInfo cardInfo, List<UserOrderResponse> orders) {
        return UserToUserResponse.convert(user, CardInfoToUserCardInfoResponse.convert(cardInfo), orders);
    }
}