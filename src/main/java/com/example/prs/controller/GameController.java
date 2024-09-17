package com.example.prs.controller;

import com.example.prs.dto.out.GameOutDto;
import com.example.prs.entity.Game;
import com.example.prs.entity.Result;
import com.example.prs.exceptions.GameNotFoundException;
import com.example.prs.exceptions.GameTerminatedException;
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
import java.util.stream.Collectors;

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
    public ResponseEntity<?> makeMove(@PathVariable UUID gameId, @PathVariable Action playerMove) throws GameNotFoundException, GameTerminatedException {
        GameResult result = gameService.makeMove(gameId, playerMove);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{gameId}/terminate")
    public ResponseEntity<?> terminate(@PathVariable UUID gameId) throws GameNotFoundException {
        log.info("Terminating game with id: {}", gameId);

        gameService.terminate(gameId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<GameOutDto>> findAll(@PathVariable UUID userId) {
        List<Game> gameEntities = gameService.getAllGamesOfUser(userId);
        List<GameOutDto> outDtos = gameEntities.stream()
                .map(e -> {
                    List<String> mappedResults = e.getResults().stream()
                            .map(Result::getName)
                            .collect(Collectors.toList());
                    return new GameOutDto(e.getGameId(), e.getPlayDate(), mappedResults, e.isTerminated());
                }).collect(Collectors.toList());
        return ResponseEntity.ok(outDtos);
    }
}
