package com.example.prs.repository;

import com.example.prs.entity.Player;
import com.example.prs.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Player, UUID> {
    Player findOneById(UUID id);
}
