package com.greedobank.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class CustomException extends RuntimeException {

  private final HttpStatus httpStatus;

  public CustomException(HttpStatus httpStatus, String msg) {
    super(msg);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getStatus() {
    return httpStatus;
  }
}
