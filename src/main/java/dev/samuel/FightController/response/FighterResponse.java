package dev.samuel.FightController.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FighterResponse(
        UUID id,
        String name,
        BigDecimal height,
        LocalDate birthDate,
        BigDecimal weight,
        List<String> fightCompetitions,
        String description,
        List<String> categories,
        LocalDateTime createdAt
        ) {
}
