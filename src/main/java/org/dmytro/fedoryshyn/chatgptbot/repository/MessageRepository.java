package org.dmytro.fedoryshyn.chatgptbot.repository;

import java.util.List;
import org.dmytro.fedoryshyn.chatgptbot.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
 List<Message> findTop10ByTelegramUserChatIdAndRoleInOrderByIdDesc(Long chatId, List<String> roles);
 List<Message> findAllByTelegramUserChatId(Long chatId, Pageable pageable);
}
