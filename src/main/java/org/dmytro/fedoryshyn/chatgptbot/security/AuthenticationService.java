package org.dmytro.fedoryshyn.chatgptbot.security;

import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserLoginRequestDto;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserLoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setToken(token);
        return userLoginResponseDto;
    }
}
