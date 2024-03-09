package com.example.orderTracking.responses.entityResponses.User.converters;

import com.example.orderTracking.model.entities.CardInfo;
import com.example.orderTracking.model.entities.Order;
import com.example.orderTracking.model.users.User;
import com.example.orderTracking.responses.entityResponses.User.UserResponse;
import com.example.orderTracking.responses.nestedResponses.cardInfo.UserCardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.cardInfo.converters.CardInfoToUserCardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.order.UserOrderResponse;
import com.example.orderTracking.responses.nestedResponses.order.converters.OrderToUserOrderResponse;
import com.example.orderTracking.responses.nestedResponses.product.converters.ProductToOrderProductResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserToUserResponse {

    public static UserResponse convert(User user, UserCardInfoResponse cardInfo, List<UserOrderResponse> orders) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .deletedAt(user.getDeletedAt())
                .email(user.getEmail())
                .cardInfo(cardInfo)
                .orders(orders)
                .build();

    }

    public static List<UserResponse> convertMultiple(List<User> users) {
        return Optional.ofNullable(users).orElse(Collections.emptyList()).stream()
                .map(user -> convert(user,
                        CardInfoToUserCardInfoResponse.convert(user.getCardInfo()),
                        OrderToUserOrderResponse.convertMultiple(user.getOrders())))
                .toList();

    }
}
