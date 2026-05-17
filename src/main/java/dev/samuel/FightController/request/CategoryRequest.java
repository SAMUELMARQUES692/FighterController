package dev.samuel.FightController.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(

        @NotBlank
        String name
) {
}
