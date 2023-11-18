package org.dmytro.fedoryshyn.chatgptbot.dto.message;

import lombok.Data;

@Data
public class MessageDto {
    private String role;
    private String content;
}
