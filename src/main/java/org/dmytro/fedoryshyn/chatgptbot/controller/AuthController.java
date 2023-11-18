package org.dmytro.fedoryshyn.chatgptbot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserLoginRequestDto;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserLoginResponseDto;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserRegistrationRequest;
import org.dmytro.fedoryshyn.chatgptbot.security.AuthenticationService;
import org.dmytro.fedoryshyn.chatgptbot.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        userService.registerUser(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto loginUser(@Valid @RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
