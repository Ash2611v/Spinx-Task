package com.application.springApplication.controller;

import com.application.springApplication.model.AuthenticationResponse;
import com.application.springApplication.model.User;
import com.application.springApplication.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/add_user")
    public AuthenticationResponse addUser(@RequestBody User user) {
        return this.authService.addUser(user);

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestBody String token
    ) {
        authService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}
