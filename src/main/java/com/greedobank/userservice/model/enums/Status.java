package com.greedobank.userservice.model.enums;

public enum Status {
  APPROVED,
  DECLINED,
  PENDING;

  public String getStatus() {
    return this.toString();
  }
}
