package com.example.prs.service;

import com.example.prs.entity.Game;
import com.example.prs.entity.Result;
import com.example.prs.exceptions.GameNotFoundException;
import com.example.prs.game.Action;
import com.example.prs.game.GameLogic;
import com.example.prs.game.GameResult;
import com.example.prs.repository.GameRepository;
import com.example.prs.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;
    private final ResultRepository resultRepository;
    private final GameLogic gameLogic;

    public UUID createNewGame() {
        Game game = new Game();
        game.setResults(new ArrayList<>());
        game.setPlayDate(LocalDate.now());
        game.setUserId(UUID.randomUUID());

        repository.save(game);
        return game.getGameId();
    }

    public GameResult makeMove(UUID gameId, Action playerMove) throws GameNotFoundException {
        Game game = repository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        GameResult gameResult = gameLogic.playGame(playerMove);
        log.info("Game result is {}", gameResult.name());

        Result resultEntity = resultRepository.findOneByName(gameResult.name());
        resultEntity.getGames().add(game);
        resultRepository.save(resultEntity);

        return gameResult;
    }

    public void terminate(UUID gameId) throws GameNotFoundException {
        Game game = repository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);
        game.setTerminated(true);
        repository.save(game);
    }
}
