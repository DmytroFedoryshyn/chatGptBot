package org.dmytro.fedoryshyn.chatgptbot.service.message;

import java.util.List;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;
import org.dmytro.fedoryshyn.chatgptbot.model.Message;
import org.springframework.data.domain.Pageable;

public interface MessageService {
    List<MessageDto> getMessageHistory(long chatId);

    void saveMessage(Message message);

    List<MessageDto> getAdminMessageHistory(long chatId, Pageable pageable);
}
