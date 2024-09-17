package com.example.prs.controller;

import com.example.prs.entity.Game;
import com.example.prs.exceptions.GameNotFoundException;
import com.example.prs.game.Action;
import com.example.prs.game.GameLogic;
import com.example.prs.game.GameResult;
import com.example.prs.repository.GameRepository;
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
    private final GameRepository repository;
    private final GameLogic gameLogic;

    @PostMapping("/start")
    public ResponseEntity<UUID> startGame() {
        Game game = new Game();
        game.setResult(GameResult.UNKNOWN);
        game.setPlayDate(LocalDate.now());
        game.setUserId(UUID.randomUUID());

        repository.save(game);

        return ResponseEntity.ok().body(game.getGameId());
    }

    @PatchMapping("/{gameId}/move/{playerMove}")
    public ResponseEntity<?> makeMove(@PathVariable UUID gameId, @PathVariable Action playerMove) throws GameNotFoundException {
        Game game = repository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);
        GameResult gameResult = gameLogic.playGame(playerMove);
        log.info("Game result is {}", gameResult.name());

        game.setResult(gameResult);
        repository.save(game);

        return ResponseEntity.ok(gameResult);
    }

    @PutMapping("/{gameId}/terminate")
    public ResponseEntity<?> terminate(@PathVariable UUID gameId) {
        log.info("Terminating game with id: {}", gameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/statistics")
    public @ResponseBody ResponseEntity<List<Game>> statistics(@PathVariable UUID userId) {
        List<Game> userGames = repository.findAllByUserId(userId);
        return ResponseEntity.ok(userGames);
    }
}
