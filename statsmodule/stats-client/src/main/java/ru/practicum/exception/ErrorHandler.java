package ru.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidateException(final ValidationException e) {
        return Map.of(
                "errorMessage", "Ошибка при выполнении запроса: " + ValidationException.class.getSimpleName(),
                "error", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, String> handleResourceNotFound(ResourceNotFoundException e) {
        return Map.of(
                "error", "Ошибка при выполнении запроса: " + ResourceNotFoundException.class.getSimpleName(),
                "errorMessage", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidException1(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        //хочу, чтобы выводилось мое и только мое сообщение
        return Map.of(
                "error", "Ошибка при выполнении запроса: " + MethodArgumentNotValidException.class.getSimpleName(),
                "errorMessage", msg
        );
    }
}