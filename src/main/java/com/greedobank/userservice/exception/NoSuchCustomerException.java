package com.greedobank.userservice.exception;

public class NoSuchCustomerException extends RuntimeException {

  public NoSuchCustomerException(String message) {
    super(message);
  }
}