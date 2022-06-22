package com.greedobank.userservice.security.details;

import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.service.impl.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

  private final PersonServiceImpl personService;

  @Override
  public PersonDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Person person = personService.getPersonByEmail(email);
    if (person == null) {
      return null;
    }
    String role = personService.getRole(person);
    return PersonDetails.personToPersonDetails(person, role);
  }
}