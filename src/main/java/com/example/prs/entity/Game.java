package com.example.prs.entity;

import com.example.prs.game.GameResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @Type(type = "uuid-char")
    private UUID userId;
    private LocalDate playDate;

    @ManyToMany(mappedBy = "games")
    private List<Result> results;

    private boolean isTerminated;
}
