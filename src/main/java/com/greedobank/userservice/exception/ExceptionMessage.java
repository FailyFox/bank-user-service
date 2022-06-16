package com.greedobank.userservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionMessage {

  private String message;

  public ExceptionMessage(String message) {
    this.message = message;
  }
}