package dev.samuel.FightController.exception;

import java.util.UUID;

public class FighterNotFoundException extends RuntimeException{

    public FighterNotFoundException(UUID id) {
        super("Fighter not found with id: " + id);
    }
}
