package com.example.prs.repository;

import com.example.prs.entity.Game;
import com.example.prs.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    List<Game> findAllByPlayer(Player user);
}
