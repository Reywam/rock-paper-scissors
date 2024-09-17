package com.example.prs.entity;

import com.example.prs.game.GameResult;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Result {
    @Id
    @GeneratedValue
    int id;
    String name;

    @ManyToMany
    @JoinTable(
            name = "game_results",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "gameId")}
    )
    List<Game> games;
}
