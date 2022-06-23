package com.greedobank.userservice.service.impl;

import static com.greedobank.userservice.util.Constants.ENTITY_PERSON;

import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.repository.PersonRepository;
import com.greedobank.userservice.security.AuthRequest;
import com.greedobank.userservice.security.AuthResponse;
import com.greedobank.userservice.security.jwt.JwtProvider;
import com.greedobank.userservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;
  private final JwtProvider jwtProvider;
  @Lazy
  private final PasswordEncoder passwordEncoder;

  @Override
  public AuthResponse authentication(AuthRequest request) {
    Person person = getPersonByEmailAndPassword(request.getEmail(),
        request.getPassword());
    String token = jwtProvider.generateToken(person.getEmail());
    return new AuthResponse(token);
  }

  @Override
  public Person getPersonByEmail(String email) {
    return personRepository.getPersonByEmail(email);
  }

  @Override
  public String getRole(Person person) {
    if (person.getCustomer() != null) {
      return "ROLE_CUSTOMER";
    } else if (person.getManager() != null) {
      return "ROLE_MANAGER";
    } else {
      return "Unauthorized Person";
    }
  }

  private Person getPersonByEmailAndPassword(String email, String password) {
    Person person = getPersonByEmail(email);
    if (passwordEncoder.matches(password, person.getPassword())) {
      return person;
    } else {
      throw new EntityNotFoundException(ENTITY_PERSON, email);
    }
  }
}