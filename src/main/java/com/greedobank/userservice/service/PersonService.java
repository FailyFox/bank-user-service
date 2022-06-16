package com.greedobank.userservice.service;

import com.greedobank.userservice.model.Person;

public interface PersonService {

  Person getPersonByEmail(String email);

  String getRole(Person person);
}