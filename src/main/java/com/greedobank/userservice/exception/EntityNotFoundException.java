package com.greedobank.userservice.exception;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(int id, String entityName) {
    super(String.format("There is no %s with id: %d", entityName, id));
  }

  public EntityNotFoundException(String entityName, String email) {
    super(String.format("There is no %s with email: %s", entityName, email));
  }
}
