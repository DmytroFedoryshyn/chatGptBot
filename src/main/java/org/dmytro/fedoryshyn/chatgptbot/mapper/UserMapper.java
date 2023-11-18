package org.dmytro.fedoryshyn.chatgptbot.mapper;

import org.dmytro.fedoryshyn.chatgptbot.config.MapperConfig;
import org.dmytro.fedoryshyn.chatgptbot.dto.user.UserRegistrationRequest;
import org.dmytro.fedoryshyn.chatgptbot.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfig.class)
@Component
public interface UserMapper {
    User toUser(UserRegistrationRequest request);


}
