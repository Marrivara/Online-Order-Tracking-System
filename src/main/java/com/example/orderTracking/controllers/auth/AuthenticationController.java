package com.example.orderTracking.controllers.auth;


import com.example.orderTracking.requests.authRequests.AuthenticationRequest;
import com.example.orderTracking.requests.authRequests.RegisterRequest;
import com.example.orderTracking.responses.authResponses.AuthenticationResponse;
import com.example.orderTracking.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity
                .ok(authenticationService
                        .register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest registerRequest
    ) {
        return ResponseEntity
                .ok(authenticationService
                        .login(registerRequest));
    }
}