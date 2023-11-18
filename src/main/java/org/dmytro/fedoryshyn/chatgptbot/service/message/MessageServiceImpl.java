package org.dmytro.fedoryshyn.chatgptbot.service.message;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;
import org.dmytro.fedoryshyn.chatgptbot.mapper.MessageMapper;
import org.dmytro.fedoryshyn.chatgptbot.model.Message;
import org.dmytro.fedoryshyn.chatgptbot.repository.MessageRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final MessageMapper messageMapper;

    @Override
    public List<MessageDto> getMessageHistory(long chatId) {
        List<MessageDto> messageHistory =
            repository.findTop10ByTelegramUserChatIdAndRoleInOrderByIdDesc(chatId, List.of("assistant", "user"))
                .stream()
                .map(messageMapper::toDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }));

        return messageHistory;
    }

    @Override
    public void saveMessage(Message message) {
        repository.save(message);
    }

    @Override
    public List<MessageDto> getAdminMessageHistory(long chatId, Pageable pageable) {
        return repository.findAllByTelegramUserChatId(chatId, pageable)
            .stream()
            .map(messageMapper::toDto)
            .collect(Collectors.toList());
    }
}
