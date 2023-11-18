package org.dmytro.fedoryshyn.chatgptbot.dto.message;

import java.util.List;
import lombok.Data;

@Data
public class SendRequestDto {
    private String model;
    private List<MessageDto> messages;
}
