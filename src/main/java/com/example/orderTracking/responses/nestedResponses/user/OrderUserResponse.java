package com.example.orderTracking.responses.nestedResponses.user;

import com.example.orderTracking.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderUserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private Date createdAt;
    private Date deletedAt;
}
