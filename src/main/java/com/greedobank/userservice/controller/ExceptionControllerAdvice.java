package com.greedobank.userservice.controller;

import com.greedobank.userservice.exception.ExceptionMessage;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> noSuchEntity(EntityNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ExceptionMessage(exception.getMessage()));
  }
}