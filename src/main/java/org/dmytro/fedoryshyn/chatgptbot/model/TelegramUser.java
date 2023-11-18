package org.dmytro.fedoryshyn.chatgptbot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "telegram_users")
@Getter
@Setter
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, name = "chat_id", unique = true)
    private Long chatId;
    @Column(nullable = false, name = "full_name")
    private String fullName;
}
