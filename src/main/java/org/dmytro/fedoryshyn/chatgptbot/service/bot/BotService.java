package org.dmytro.fedoryshyn.chatgptbot.service.bot;

import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;

public interface BotService {
    void sendAdminMessage(long chatId, MessageDto messageDto) throws Exception;
}
