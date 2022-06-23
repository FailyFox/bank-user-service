package com.greedobank.userservice.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

  @NonNull
  private String email;
  @NonNull
  private String password;
}