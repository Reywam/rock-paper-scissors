package com.example.prs.dto.out;

import com.example.prs.game.GameResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameOutDto {
    private UUID id;
    private LocalDate playDate;
    private List<String> results;
    private boolean isTerminated;
}
