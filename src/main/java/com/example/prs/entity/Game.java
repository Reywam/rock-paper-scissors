package com.example.prs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Game {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID gameId;

    @ManyToOne
    @JoinColumn(name = "id")
    private Player player;
    private LocalDate playDate;

    @ManyToMany(mappedBy = "games")
    private List<Result> results;

    private boolean isTerminated;
}
