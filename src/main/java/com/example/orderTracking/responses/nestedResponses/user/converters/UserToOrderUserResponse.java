package com.example.orderTracking.responses.nestedResponses.user.converters;

import com.example.orderTracking.model.users.User;
import com.example.orderTracking.responses.nestedResponses.user.OrderUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserToOrderUserResponse {

    public static OrderUserResponse convert(User user){
        return OrderUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }
}
