package org.dmytro.fedoryshyn.chatgptbot.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.bot.ChatGPTBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
public class BotRegister {
    private final ChatGPTBot chatGPTBot;

    @PostConstruct
    public void init() throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(chatGPTBot);
    }
}
