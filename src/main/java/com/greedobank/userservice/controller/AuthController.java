package com.greedobank.userservice.controller;

import com.greedobank.userservice.security.AuthRequest;
import com.greedobank.userservice.security.AuthResponse;
import com.greedobank.userservice.service.PersonService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final PersonService personService;

  @PostMapping("/auth")
  public AuthResponse authentication(@Valid @RequestBody AuthRequest request) {
    return personService.authentication(request);
  }
}