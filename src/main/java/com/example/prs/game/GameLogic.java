package com.example.prs.game;

import com.example.prs.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.example.prs.game.Action.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameLogic {
    private final static Action[] POSSIBLE_MOVES = Action.values();

    public GameResult playGame(Action playerMove) {
        Action computerMove = makeMove();
        log.info("Computer move is {}, player move {}", computerMove.name(), playerMove.name());
        return checkResult(playerMove, computerMove);
    }

    private Action makeMove() {
        int nextMoveId = new Random().nextInt(POSSIBLE_MOVES.length);
        return POSSIBLE_MOVES[nextMoveId];
    }

    private GameResult checkResult(Action playerMove, Action computerMove) {
        if (playerMove == ROCK) {
            if (computerMove == SCISSORS) {
                return GameResult.PLAYER_WIN;
            }
            if (computerMove == PAPER) {
                return GameResult.COMPUTER_WIN;
            }
            return GameResult.DRAW;
        }

        if (playerMove == PAPER) {
            if (computerMove == ROCK) {
                return GameResult.PLAYER_WIN;
            }
            if (computerMove == SCISSORS) {
                return GameResult.COMPUTER_WIN;
            }
            return GameResult.DRAW;
        }

        if (playerMove == SCISSORS) {
            if (computerMove == PAPER) {
                return GameResult.PLAYER_WIN;
            }
            if (computerMove == ROCK) {
                return GameResult.COMPUTER_WIN;
            }
            return GameResult.DRAW;
        }
        return GameResult.UNKNOWN;
    }
}
