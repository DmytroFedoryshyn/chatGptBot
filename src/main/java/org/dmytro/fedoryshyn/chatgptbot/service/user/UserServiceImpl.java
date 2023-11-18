package org.dmytro.fedoryshyn.chatgptbot.service.user;

import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserRegistrationRequest;
import org.dmytro.fedoryshyn.chatgptbot.exception.RegistrationException;
import org.dmytro.fedoryshyn.chatgptbot.mapper.UserMapper;
import org.dmytro.fedoryshyn.chatgptbot.model.User;
import org.dmytro.fedoryshyn.chatgptbot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    @Override
    public void registerUser(UserRegistrationRequest request) {
        if (userRepository.findUserByLogin(request.getLogin()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }

        User user = userMapper.toUser(request);
        user.setPassword(encoder.encode(request.getPassword()));
        
        userRepository.save(user);
    }
}
