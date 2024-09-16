package com.example.prs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {
    @PostMapping("/start")
    public ResponseEntity<UUID> startGame() {
        return ResponseEntity.ok().body(UUID.randomUUID());
    }

    @PatchMapping("/{gameId}/move")
    public ResponseEntity<?> makeMove(@PathVariable UUID gameId) {
        log.info("Make move in game with id: {}", gameId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{gameId}/terminate")
    public ResponseEntity<?> terminate(@PathVariable UUID gameId) {
        log.info("Terminating game with id: {}", gameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> statistics() {
        return ResponseEntity.ok().build();
    }
}
