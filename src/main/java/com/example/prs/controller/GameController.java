package com.example.prs.controller;

import com.example.prs.dto.out.GameOutDto;
import com.example.prs.entity.Game;
import com.example.prs.exceptions.GameNotFoundException;
import com.example.prs.exceptions.GameTerminatedException;
import com.example.prs.game.Action;
import com.example.prs.game.GameResult;
import com.example.prs.mapper.GameMapper;
import com.example.prs.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<GameOutDto> startGame() {
        Game game = gameService.createNewGame();
        return ResponseEntity.ok().body(GameMapper.map(game));
    }

    @PatchMapping("/{gameId}/move/{playerMove}")
    public ResponseEntity<?> makeMove(@PathVariable UUID gameId, @PathVariable Action playerMove)
            throws GameNotFoundException, GameTerminatedException {
        GameResult result = gameService.makeMove(gameId, playerMove);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{gameId}/terminate")
    public ResponseEntity<?> terminate(@PathVariable UUID gameId) throws GameNotFoundException {
        log.info("Terminating game with id: {}", gameId);

        gameService.terminate(gameId);

        return ResponseEntity.ok().build();
    }
}
