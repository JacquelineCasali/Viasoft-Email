package com.viasoft.exception;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento de exceção personalizada com múltiplas mensagens
    @ExceptionHandler(MultiplasRegrasException.class)
    public ResponseEntity<?> handleMultiplasRegras(MultiplasRegrasException ex) {
        return ResponseEntity.badRequest().body(Map.of("erros", ex.getMensagens()));
    }
    // Tratamento de erros de validação em @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of("mensagem", error.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(Map.of("erros", erros));
    }

    // Tratamento de erros de regra de negócio ou dados inválidos
    @ExceptionHandler({
            EmailProcessingException.class,
            ConstraintViolationException.class
                })
    public ResponseEntity<?> handlePersonalizadas(RuntimeException ex) {
        return ResponseEntity.badRequest().body(Map.of("erros", List.of(ex.getMessage())));
    }
    // Tratamento genérico para exceções inesperadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOutras(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("erros", List.of("Erro interno: " + ex.getMessage())));
    }
}
