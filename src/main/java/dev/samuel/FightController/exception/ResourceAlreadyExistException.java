package dev.samuel.FightController.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String email) {
        super("Email " + "'" + email + "' " + "already is use");
    }
}
