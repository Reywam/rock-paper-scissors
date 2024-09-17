package com.example.prs.controller;

import com.example.prs.dto.out.GameOutDto;
import com.example.prs.entity.Game;
import com.example.prs.entity.Player;
import com.example.prs.entity.Result;
import com.example.prs.mapper.GameMapper;
import com.example.prs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping("/{userId}/statistics")
    public ResponseEntity<List<GameOutDto>> statistics(@PathVariable UUID userId) {
        Player player = repository.findOneById(userId);

        List<GameOutDto> outDtos = player.getGames().stream()
                .map(GameMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(outDtos);
    }
}
