package com.example.prs.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Player {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @OneToMany(mappedBy = "player")
    private List<Game> games;
}
