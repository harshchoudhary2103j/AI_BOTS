package com.example.AI_BOTS.controller;

import com.example.AI_BOTS.dto.VibeMatchResponseDto;
import com.example.AI_BOTS.service.VibeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VibeController {

    private final VibeService vibeService;

    public VibeController(VibeService vibeService) {
        this.vibeService = vibeService;
    }

    @GetMapping("/match-vibe")
    public VibeMatchResponseDto matchVibe(@RequestParam String feeling) {
        return vibeService.getSimilarSongs(feeling);
    }
}
