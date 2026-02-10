package com.example.AI_BOTS.service;

import com.example.AI_BOTS.dto.PolicyAnswerDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeHandBookService {
    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    @Value("classpath:policy.pdf")
    Resource policy;

    //Ingesting into vector store
//    @PostConstruct
    public void ingestPolicy(){
        PagePdfDocumentReader reader = new PagePdfDocumentReader(policy);
        List<Document>documents = reader.read();
        TokenTextSplitter textSplitter = TokenTextSplitter.builder()
                .withChunkSize(200)
                .build();
        List<Document>chunks = textSplitter.apply(documents);
        vectorStore.add(chunks);
    }

    public PolicyAnswerDto getPolicy(String question) {
        var res = vectorStore.similaritySearch(SearchRequest.builder()
                        .query(question)

                        .filterExpression("file_name == 'policy.pdf'")
                        .topK(4)
                .build());

        if(res.isEmpty()){
            return new PolicyAnswerDto("No policy found","Try a different question");
        }

        String context = res.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        String answer = chatClient.prompt()
                .system("""
                        You are an HR policy assistant.
                        Answer ONLY using the provided context.
                        If the answer is not present, say you don't know.
                        """)
                .user("""
                        Context:
                        %s

                        Question:
                        %s
                        """.formatted(context, question))
                .call()
                .content();

        return new PolicyAnswerDto(answer,"policy.pdf");
    }
}
