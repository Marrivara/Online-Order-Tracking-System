package com.example.orderTracking.responses.entityResponses.User;

import com.example.orderTracking.enums.Gender;
import com.example.orderTracking.enums.Role;
import com.example.orderTracking.responses.nestedResponses.cardInfo.UserCardInfoResponse;
import com.example.orderTracking.responses.nestedResponses.order.UserOrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Integer age;
    private String phoneNumber;
    private Role role;
    private Date createdAt;
    private Date deletedAt;

    private UserCardInfoResponse cardInfo;
    private List<UserOrderResponse> orders;
}
