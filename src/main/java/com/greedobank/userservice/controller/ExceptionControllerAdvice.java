package com.greedobank.userservice.controller;

import com.greedobank.userservice.exception.ExceptionMessage;
import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> noSuchEntity(EntityNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ExceptionMessage(exception.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> unauthorized(AuthenticationException exception) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ExceptionMessage(exception.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> forbidden(AccessDeniedException exception) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(new ExceptionMessage(exception.getMessage()));
  }
}