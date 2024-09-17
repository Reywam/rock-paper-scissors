package com.example.prs.mapper;

import com.example.prs.dto.out.GameOutDto;
import com.example.prs.entity.Game;
import com.example.prs.entity.Result;

import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {
    public static GameOutDto map(Game game) {
        List<String> mappedResults = game.getResults().stream()
                .map(Result::getName)
                .collect(Collectors.toList());
        return new GameOutDto(game.getGameId(), game.getPlayDate(), mappedResults, game.isTerminated());
    }
}
