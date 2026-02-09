package com.example.AI_BOTS.controller;

import com.example.AI_BOTS.dto.PoemResponseDTO;
import com.example.AI_BOTS.service.PoetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/poem")
@RestController
@RequiredArgsConstructor
public class PoetController {
    private final PoetService poetService;

    @GetMapping
    public PoemResponseDTO getPoem(
            @RequestParam String topic,
            @RequestParam(defaultValue = "English") String lang
    ) {
        return poetService.generatePoem(topic, lang);
    }
}
