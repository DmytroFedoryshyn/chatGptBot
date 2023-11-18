package org.dmytro.fedoryshyn.chatgptbot.repository;

import java.util.Optional;
import org.dmytro.fedoryshyn.chatgptbot.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
    Optional<TelegramUser> getByChatId(long chatId);

}
