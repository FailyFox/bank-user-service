package com.greedobank.userservice.security.details;

import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

  private final PersonService personService;

  @Override
  public PersonDetails loadUserByUsername(String email) {
    Person person = personService.getPersonByEmail(email);
    if (person == null) {
      return null;
    }
    String role = personService.getRole(person);
    return PersonDetails.personToPersonDetails(person, role);
  }
}
