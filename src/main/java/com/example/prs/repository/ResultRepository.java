package com.example.prs.repository;

import com.example.prs.entity.Game;
import com.example.prs.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<Result, UUID> {
    public Result findOneByName(String name);
}
