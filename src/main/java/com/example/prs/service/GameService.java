package com.example.prs.service;

import com.example.prs.entity.Game;
import com.example.prs.entity.Player;
import com.example.prs.entity.Result;
import com.example.prs.exceptions.GameNotFoundException;
import com.example.prs.exceptions.GameTerminatedException;
import com.example.prs.game.Action;
import com.example.prs.game.GameLogic;
import com.example.prs.game.GameResult;
import com.example.prs.repository.GameRepository;
import com.example.prs.repository.ResultRepository;
import com.example.prs.repository.UserRepository;
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
    private final GameRepository gameRepository;
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;

    private final GameLogic gameLogic;
    // Predefined user for demonstration purposes
    private final UUID USER_ID = UUID.fromString("ab18c338-b95e-4a2a-aa04-2e950d9fa909");

    public Game createNewGame() {
        Player player = userRepository.findOneById(USER_ID);

        Game game = new Game();
        game.setResults(new ArrayList<>());
        game.setPlayDate(LocalDate.now());
        game.setPlayer(player);

        gameRepository.save(game);

        return game;
    }

    public GameResult makeMove(UUID gameId, Action playerMove) throws GameNotFoundException, GameTerminatedException {
        Game game = findGame(gameId);

        if (game.isTerminated()) {
            throw new GameTerminatedException();
        }

        GameResult gameResult = gameLogic.playGame(playerMove);
        log.info("Game result is {}", gameResult.name());

        Result resultEntity = resultRepository.findOneByName(gameResult.name());
        resultEntity.getGames().add(game);
        resultRepository.save(resultEntity);

        return gameResult;
    }

    public void terminate(UUID gameId) throws GameNotFoundException {
        Game game = findGame(gameId);
        game.setTerminated(true);
        gameRepository.save(game);
    }

    private Game findGame(UUID gameId) throws GameNotFoundException {
        return gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
    }
}
