package dev.samuel.FightController.response;

import java.util.List;

public record UserResponse(
        Long id,
        String name,
        String email,
        List<String> scopes

) {
}
