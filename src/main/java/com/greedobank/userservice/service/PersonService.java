package com.greedobank.userservice.service;


import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.security.AuthRequest;
import com.greedobank.userservice.security.AuthResponse;

public interface PersonService {

  AuthResponse authentication(AuthRequest request);

  Person getPersonByEmail(String email);

  String getRole(Person person);

}