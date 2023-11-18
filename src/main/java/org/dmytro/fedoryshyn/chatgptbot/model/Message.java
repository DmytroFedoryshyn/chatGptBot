package org.dmytro.fedoryshyn.chatgptbot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;
    @OneToOne
    @JoinColumn(name = "telegram_user", referencedColumnName = "id")
    private TelegramUser telegramUser;
    @Column(nullable = false)
    private long timestamp;
}
