package org.dmytro.fedoryshyn.chatgptbot.service.telegramUser;

import java.util.List;
import org.dmytro.fedoryshyn.chatgptbot.model.TelegramUser;
import org.springframework.data.domain.Pageable;

public interface TelegramUserService {
    void registerUser(TelegramUser telegramUser);

    TelegramUser getUserByChatId(long chatId);

    List<TelegramUser> getAllUsers(Pageable pageable);
}
