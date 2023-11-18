package org.dmytro.fedoryshyn.chatgptbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.MessageDto;
import org.dmytro.fedoryshyn.chatgptbot.dto.message.SendRequestDto;
import org.dmytro.fedoryshyn.chatgptbot.mapper.MessageMapper;
import org.dmytro.fedoryshyn.chatgptbot.model.Message;
import org.dmytro.fedoryshyn.chatgptbot.model.TelegramUser;
import org.dmytro.fedoryshyn.chatgptbot.service.message.MessageService;
import org.dmytro.fedoryshyn.chatgptbot.util.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class Gpt3Service {
    @Value("${openai.api-key}")
    private String openaiApiKey;

    @Value("${openai.gpt3.model}")
    private String gpt3Model;

    @Value("${chat.gpt-url}")
    private String gpt3ApiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final MessageService messageService;
    private final MessageMapper messageMapper;
    private List<MessageDto> messageHistory;

    @SneakyThrows
    public String generateGptResponse(String userMessageText, TelegramUser user) {
        if (messageHistory == null) {
            messageHistory = messageService.getMessageHistory(user.getChatId());
        }

        Message message = new Message();
        message.setRole("user");
        message.setContent(userMessageText);
        message.setTelegramUser(user);
        message.setTimestamp(System.currentTimeMillis());

        messageService.saveMessage(message);
        messageHistory.add(messageMapper.toDto(message));

        messageHistory = TokenUtil.truncateMessages(messageHistory);

        SendRequestDto sendRequestDto = new SendRequestDto();
        sendRequestDto.setModel(gpt3Model);
        sendRequestDto.setMessages(messageHistory);

        System.out.println(objectMapper.writeValueAsString(sendRequestDto.getMessages()));

        String apiResponse = restTemplate.postForObject(gpt3ApiUrl, createHttpEntity(objectMapper.writeValueAsString(sendRequestDto)), String.class);

        String chatGptResponse = extractAssistantMessage(apiResponse);

        Message chatGptMessage = new Message();
        chatGptMessage.setRole("assistant");
        chatGptMessage.setContent(chatGptResponse);
        chatGptMessage.setTimestamp(System.currentTimeMillis());
        chatGptMessage.setTelegramUser(user);
        messageService.saveMessage(chatGptMessage);

        messageHistory.add(messageMapper.toDto(chatGptMessage));

        return chatGptResponse;
    }

    private String extractAssistantMessage(String apiResponse) {
        try {
            JsonNode jsonNode = objectMapper.readTree(apiResponse);

            JsonNode choicesNode = jsonNode.path("choices");
            if (choicesNode.isArray() && !choicesNode.isEmpty()) {
                JsonNode firstChoice = choicesNode.get(0);
                JsonNode messageNode = firstChoice.path("message");

                if (messageNode.isObject() && messageNode.has("content")) {
                    return messageNode.path("content").asText();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    private HttpEntity<String> createHttpEntity(String requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openaiApiKey);
        return new HttpEntity<>(requestBody, headers);
    }
}
