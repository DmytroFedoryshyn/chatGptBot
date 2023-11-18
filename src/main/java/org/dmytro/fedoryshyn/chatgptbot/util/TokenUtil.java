package org.dmytro.fedoryshyn.chatgptbot.util;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;
import java.util.ArrayList;
import java.util.List;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;

public class TokenUtil {
    private static final int MAX_TOKENS = 3000;

    public static List<MessageDto> truncateMessages(List<MessageDto> messageHistory) {
        int totalTokens = 0;
        List<MessageDto> filteredMessages = new ArrayList<>();

        for (int i = messageHistory.size() - 1; i >= 0; i--) {
            MessageDto messageDto = messageHistory.get(i);
            int messageTokens = countTokens(messageDto.getContent());

            if (totalTokens + messageTokens <= MAX_TOKENS) {
                filteredMessages.add(0, messageDto);
                totalTokens += messageTokens;
            } else {
                break;
            }
        }

        return filteredMessages;
    }

    public static int countTokens(String content) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncoding(EncodingType.CL100K_BASE);
        return enc.countTokens(content);
    }
}
