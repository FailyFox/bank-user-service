package com.greedobank.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String entityName, int id) {
    super(String.format("There is no %s with id %d", entityName, id));
  }

  public EntityNotFoundException(String entityName, String email) {
    super(String.format("There is no %s with email %s", entityName, email));
  }
}