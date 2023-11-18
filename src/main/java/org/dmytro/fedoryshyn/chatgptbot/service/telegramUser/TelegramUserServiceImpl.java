package org.dmytro.fedoryshyn.chatgptbot.service.telegramUser;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.model.TelegramUser;
import org.dmytro.fedoryshyn.chatgptbot.repository.TelegramUserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository repository;
    @Override
    public void registerUser(TelegramUser telegramUser) {
        repository.save(telegramUser);
    }

    @Override
    public TelegramUser getUserByChatId(long chatId) {
        return repository.getByChatId(chatId).orElseThrow(()
            -> new RuntimeException("Telegram user not found"));
    }

    @Override
    public List<TelegramUser> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }
}
