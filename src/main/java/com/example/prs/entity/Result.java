package com.example.prs.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
