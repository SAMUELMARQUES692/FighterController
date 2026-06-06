package dev.samuel.FightController.exception;

public class UserOrPasswordIncorectException extends RuntimeException{
    public UserOrPasswordIncorectException(String username, String password) {
        super("Email or Password are incorrect");
    }
}
