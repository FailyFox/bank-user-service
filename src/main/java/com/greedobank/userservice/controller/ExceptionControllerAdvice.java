package com.greedobank.userservice.controller;

import com.greedobank.userservice.exception.CustomException;
import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.exception.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> noSuchEntity(EntityNotFoundException e) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ExceptionMessage(e.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> unauthorized(AuthenticationException e) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ExceptionMessage(e.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> forbidden(AccessDeniedException e) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(new ExceptionMessage(e.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> custom(CustomException e) {
    return ResponseEntity
        .status(e.getStatus())
        .body(new ExceptionMessage(e.getMessage()));
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> validArgs(IllegalArgumentException e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ExceptionMessage(e.getMessage()));
  }
}
