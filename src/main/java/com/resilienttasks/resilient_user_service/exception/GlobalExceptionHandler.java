package com.resilienttasks.resilient_user_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.resilienttasks.resilient_user_service.dto.ErrorResponse;
import java.time.LocalDateTime;
import com.resilienttasks.resilient_user_service.exception.*;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(
            ErrorResponse.builder()
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now().toString())
            .status(400)
            .error("Bad Request")
            .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        return ResponseEntity.status(404).body(
            ErrorResponse.builder()
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now().toString())
            .status(404)
            .error("Not Found")
            .build()
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        return ResponseEntity.status(403).body(
            ErrorResponse.builder()
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now().toString())
            .status(403)
            .error("Forbidden")
            .build()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(
            ErrorResponse.builder()
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now().toString())
            .status(400)
            .error("Bad Request")
            .build()
        );
    }
}