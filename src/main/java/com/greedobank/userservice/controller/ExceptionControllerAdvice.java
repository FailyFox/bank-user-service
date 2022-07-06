package com.greedobank.userservice.controller;

import com.greedobank.userservice.exception.CustomException;
import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.exception.ExceptionMessage;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<Object> noSuchEntity(EntityNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ExceptionMessage(exception.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<Object> unauthorized(AuthenticationException exception) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ExceptionMessage(exception.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<Object> forbidden(AccessDeniedException exception) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(new ExceptionMessage(exception.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<Object> custom(CustomException exception) {
    return ResponseEntity
        .status(exception.getStatus())
        .body(new ExceptionMessage(exception.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<Object> validArgs(IllegalArgumentException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ExceptionMessage(exception.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> validException(MethodArgumentNotValidException e) {
    List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ExceptionMessage(
            fieldErrors.stream()
                .filter(Objects::nonNull)
                .map(msg -> msg.getDefaultMessage().concat("; "))
                .reduce("", String::concat)));
  }
}