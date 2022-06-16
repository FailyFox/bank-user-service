package com.greedobank.userservice.service.impl;

import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.repository.PersonRepository;
import com.greedobank.userservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  @Override
  public Person getPersonByEmail(String email) {
    return personRepository.getPersonByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("person", email));
  }

  public String getRole(Person person) {
    if (person.getCustomer() != null) {
      return "ROLE_CUSTOMER";
    } else if (person.getManager() != null) {
      return "ROLE_MANAGER";
    } else {
      return "Unauthorized person";
    }
  }
}