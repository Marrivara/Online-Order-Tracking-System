package com.example.orderTracking.services.Interfaces;

import com.example.orderTracking.model.users.User;
import com.example.orderTracking.requests.authRequests.RegisterRequest;
import com.example.orderTracking.responses.entityResponses.User.UserResponse;

import java.util.List;

public interface UserServiceInterface {
    List<UserResponse> getAllUsers();

    User getUserByEmail(String userEmail);

    UserResponse updateUser(Integer id, RegisterRequest userRequest);
}
