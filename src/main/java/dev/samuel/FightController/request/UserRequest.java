package dev.samuel.FightController.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

public record UserRequest(
        @NotBlank
        String name,

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotEmpty
        @NotNull
        List<Long> scopes

) {
}