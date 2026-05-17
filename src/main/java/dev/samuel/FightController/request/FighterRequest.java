package dev.samuel.FightController.request;

import dev.samuel.FightController.enums.FightCompetition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FighterRequest(

        @NotBlank
        String name,

        @NotNull @Positive
        BigDecimal height,

        @NotNull
        LocalDate birthDate,

        @NotNull @Positive
        BigDecimal weight,

        @NotNull
        List<FightCompetition> fightCompetitions,

        String description,

        @NotNull
        List<UUID> categoryIds
) {
}
