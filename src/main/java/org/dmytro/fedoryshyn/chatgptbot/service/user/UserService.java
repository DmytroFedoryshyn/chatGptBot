package org.dmytro.fedoryshyn.chatgptbot.service.user;

import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserRegistrationRequest;

public interface UserService {
    void registerUser(UserRegistrationRequest request);
}
