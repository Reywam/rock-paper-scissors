package com.example.prs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    @PostMapping("/start")
    public ResponseEntity<?> startGame() {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/move")
    public ResponseEntity<?> makeMove() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/terminate")
    public ResponseEntity<?> terminate() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> statistics() {
        return ResponseEntity.ok().build();
    }
}
