package org.dmytro.fedoryshyn.chatgptbot.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;
import org.dmytro.fedoryshyn.chatgptbot.model.TelegramUser;
import org.dmytro.fedoryshyn.chatgptbot.service.bot.BotService;
import org.dmytro.fedoryshyn.chatgptbot.service.message.MessageService;
import org.dmytro.fedoryshyn.chatgptbot.service.telegramUser.TelegramUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@ControllerAdvice
@RequiredArgsConstructor
public class AdminController {
    private final BotService botService;
    private final MessageService messageService;
    private final TelegramUserService telegramUserService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("send-message/{chatId}")
    public void sendMessage(@PathVariable long chatId, @RequestBody MessageDto content)
        throws Exception {
        botService.sendAdminMessage(chatId, content);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("message-history/{chatId}")
    public List<MessageDto> viewMessageHistory(@PathVariable long chatId,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return messageService.getAdminMessageHistory(chatId, PageRequest.of(page, size));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("telegram_users")
    public List<TelegramUser> getTelegramUsers(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
         return telegramUserService.getAllUsers(PageRequest.of(page, size));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("telegram_user/{chatId}")
    public TelegramUser getTelegramUser(@PathVariable Long chatId) {
        return telegramUserService.getUserByChatId(chatId);
    }
}
