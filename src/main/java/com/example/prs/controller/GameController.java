package com.example.prs.controller;

import com.example.prs.entity.Game;
import com.example.prs.exceptions.GameNotFoundException;
import com.example.prs.game.Action;
import com.example.prs.game.GameLogic;
import com.example.prs.game.GameResult;
import com.example.prs.repository.GameRepository;
import com.example.prs.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<UUID> startGame() {
        UUID gameId = gameService.createNewGame();
        return ResponseEntity.ok().body(gameId);
    }

    @PatchMapping("/{gameId}/move/{playerMove}")
    public ResponseEntity<?> makeMove(@PathVariable UUID gameId, @PathVariable Action playerMove) throws GameNotFoundException {
        GameResult result = gameService.makeMove(gameId, playerMove);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{gameId}/terminate")
    public ResponseEntity<?> terminate(@PathVariable UUID gameId) throws GameNotFoundException {
        log.info("Terminating game with id: {}", gameId);

        gameService.terminate(gameId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/statistics")
    public @ResponseBody ResponseEntity<List<Game>> statistics(@PathVariable UUID userId) {
        //List<Game> userGames = repository.findAllByUserId(userId);
        return ResponseEntity.ok().build();
    }
}
