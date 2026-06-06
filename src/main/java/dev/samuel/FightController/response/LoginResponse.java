package dev.samuel.FightController.response;

import lombok.Builder;

@Builder
public record LoginResponse(

        String accessToken,
        Long expiresIn
) {
}
