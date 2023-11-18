package org.dmytro.fedoryshyn.chatgptbot.service.bot;

import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.bot.ChatGPTBot;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;
import org.dmytro.fedoryshyn.chatgptbot.mapper.MessageMapper;
import org.dmytro.fedoryshyn.chatgptbot.model.Message;
import org.dmytro.fedoryshyn.chatgptbot.service.message.MessageService;
import org.dmytro.fedoryshyn.chatgptbot.service.telegramUser.TelegramUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService{
    private final ChatGPTBot bot;
    private final MessageService messageService;
    private final MessageMapper messageMapper;
    private final TelegramUserService telegramUserService;

    @Override
    public void sendAdminMessage(long chatId, MessageDto messageDto) throws Exception {
        messageDto.setRole("admin");

        Message message = messageMapper.toMessage(messageDto);
        message.setTelegramUser(telegramUserService.getUserByChatId(chatId));
        message.setTimestamp(System.currentTimeMillis());
        messageService.saveMessage(message);
        
        bot.sendAdminMessage(chatId, messageDto.getContent());
    }
}
