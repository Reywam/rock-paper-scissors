package com.example.prs.game;

import java.util.Random;

import static com.example.prs.game.Action.*;

public class GameLogic {
    private final static Action[] POSSIBLE_MOVES = Action.values();

    public Action makeMove() {
        int nextMoveId = new Random().nextInt(POSSIBLE_MOVES.length);
        return POSSIBLE_MOVES[nextMoveId];
    }

    public GameResult checkResult(Action playerMove, Action computerMove) {
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
