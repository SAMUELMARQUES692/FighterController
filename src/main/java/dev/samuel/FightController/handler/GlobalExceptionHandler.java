package dev.samuel.FightController.handler;

import dev.samuel.FightController.exception.CategoryNotFoundException;
import dev.samuel.FightController.exception.FighterNotFoundException;
import dev.samuel.FightController.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
                "CATEGORY_NOT_FOUND",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(FighterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(FighterNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
                "FIGHTER_NOT_FOUND",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    // Fallback para erros inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unexpected error: ", ex); // adicione isso
        ErrorResponse error = new ErrorResponse(
                "INTERNAL_ERROR",
                "An unexpected error occurred",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
