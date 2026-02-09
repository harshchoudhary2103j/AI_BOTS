package com.example.AI_BOTS.service;

import com.example.AI_BOTS.dto.VibeMatchResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VibeService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @PostConstruct
    public void ingestSongs() {

        List<Document> documents = List.of(
                new Document(
                        "A song about helping someone through sadness and emotional pain",
                        Map.of("title", "Fix You", "genre", "Rock")
                ),
                new Document(
                        "Feeling emotionally overwhelmed and disconnected from life",
                        Map.of("title", "Numb", "genre", "Rock")
                ),
                new Document(
                        "Loneliness, self reflection, and walking alone through struggles",
                        Map.of("title", "Boulevard of Broken Dreams", "genre", "Rock")
                ),
                new Document(
                        "A celebration of joy, positivity, and happiness",
                        Map.of("title", "Happy", "genre", "Pop")
                )
        );

        vectorStore.add(documents);
    }

    public VibeMatchResponseDto getSimilarSongs(String feeling) {
        var res =  vectorStore.similaritySearch(SearchRequest.builder()
                .query(feeling)
                        .topK(1)
                        .similarityThreshold(0.5)
                .build());
        if(res.isEmpty()){
            return new VibeMatchResponseDto("No song found","N/A", "Try a different song");
        }

        var doc = res.get(0);

        return new VibeMatchResponseDto(
                doc.getMetadata().get("title").toString(),
                doc.getMetadata().get("genre").toString(),
                "Matched using semantic similarity on your mood"
        );


    }
}
