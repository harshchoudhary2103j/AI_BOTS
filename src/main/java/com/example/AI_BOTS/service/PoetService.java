package com.example.AI_BOTS.service;

import com.example.AI_BOTS.dto.PoemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoetService {
    private final ChatClient chatClient;
    public PoemResponseDTO generatePoem(String topic, String lang) {
        return chatClient
                .prompt()
                .system("""
                        You are a sarcastic poet.
                        Always respond in valid JSON only.
                        """)
                .user("""
                        Write a sarcastic poem on "%s" in %s.
                        The response must contain:
                        - title
                        - poemText
                        - rhymeScheme
                        """.formatted(topic, lang))
                .call()
                .entity(PoemResponseDTO.class);
    }
}
