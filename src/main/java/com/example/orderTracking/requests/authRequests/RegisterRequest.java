package com.example.orderTracking.requests.authRequests;


import com.example.orderTracking.enums.Gender;
import com.example.orderTracking.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String password;
    private Role role;
    private String address;

}