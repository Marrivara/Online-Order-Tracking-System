package com.example.orderTracking.services.Interfaces;

import com.example.orderTracking.responses.entityResponses.User.UserResponse;

import java.util.List;

public interface UserServiceInterface {
    List<UserResponse> getAllUsers();
}
