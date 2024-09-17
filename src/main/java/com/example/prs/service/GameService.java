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
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;

    private final GameLogic gameLogic;
    private final UUID USER_ID = UUID.fromString("ab18c338-b95e-4a2a-aa04-2e950d9fa909");

    public UUID createNewGame() {
        Player player = userRepository.findOneById(USER_ID);

        Game game = new Game();
        game.setResults(new ArrayList<>());
        game.setPlayDate(LocalDate.now());
        game.setPlayer(player);
        repository.save(game);

        return game.getGameId();
    }

    public GameResult makeMove(UUID gameId, Action playerMove) throws GameNotFoundException, GameTerminatedException {
        Game game = repository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

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
        Game game = repository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);
        game.setTerminated(true);
        repository.save(game);
    }

    public List<Game> getAllGamesOfUser(UUID userId) {
        Player player = userRepository.findOneById(userId);
        return repository.findAllByPlayer(player);
    }
}
