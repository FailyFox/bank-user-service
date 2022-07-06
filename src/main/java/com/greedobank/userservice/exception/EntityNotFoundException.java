package com.greedobank.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  private static final String THERE_IS_NO_RECORD_BY_ID_MSG = "There is no %s with id %d";
  private static final String THERE_IS_NO_RECORD_WITH_EMAIL_MSG = "There is no %s with email %s";

  public EntityNotFoundException(String entityName, int id) {
    super(String.format(THERE_IS_NO_RECORD_BY_ID_MSG, entityName, id));
  }

  public EntityNotFoundException(String entityName, String email) {
    super(String.format(THERE_IS_NO_RECORD_WITH_EMAIL_MSG, entityName, email));
  }
}