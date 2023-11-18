package org.dmytro.fedoryshyn.chatgptbot.mapper;

import org.dmytro.fedoryshyn.chatgptbot.config.MapperConfig;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;
import org.dmytro.fedoryshyn.chatgptbot.model.Message;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfig.class)
@Component
public interface MessageMapper {
    MessageDto toDto(Message message);

    Message toMessage(MessageDto dto);
}
