package org.dmytro.fedoryshyn.chatgptbot.bot;

import org.dmytro.fedoryshyn.chatgptbot.model.TelegramUser;
import org.dmytro.fedoryshyn.chatgptbot.service.Gpt3Service;
import org.dmytro.fedoryshyn.chatgptbot.service.telegramUser.TelegramUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ChatGPTBot extends TelegramLongPollingBot {
    private final Gpt3Service chatGptService;
    private final TelegramUserService telegramUserService;

    public ChatGPTBot(Gpt3Service chatGptService, TelegramUserService telegramUserService) {
        super("YOUR_BOT_TOKEN");
        this.chatGptService = chatGptService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null) {
            if (update.getMessage().getText().equals("/start")) {
                handleStartCommand(update);
            } else {
                Message userMessage = update.getMessage();
                String userMessageText = userMessage.getText();
                long chatId = update.getMessage().getChatId();

                SendMessage message = new SendMessage();
                message.setChatId(chatId);

                TelegramUser user = telegramUserService.getUserByChatId(chatId);

                message.setText(chatGptService.generateGptResponse(userMessageText, user));

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void handleStartCommand(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Welcome to ChatGPT bot! Ask something!");

        telegramUserService.registerUser(createNewUser(update));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private TelegramUser createNewUser(Update update) {
        Long chatId = update.getMessage().getChatId();
        String firstName = update.getMessage().getFrom().getFirstName();
        String lastName = update.getMessage().getFrom().getLastName();
        String username = update.getMessage().getFrom().getUserName();

        String fullName = firstName;
        if (lastName != null) {
            fullName += " " + lastName;
        }

        if (username != null) {
            fullName += " - @" + username;
        }

        TelegramUser newUser = new TelegramUser();
        newUser.setChatId(chatId);
        newUser.setFullName(fullName);

        return newUser;
    }

    public void sendAdminMessage(long chatId, String text) throws Exception {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        execute(sendMessage);
    }

    @Override
    public String getBotUsername() {
        return "DmytroChatGPTBot";
    }
}
