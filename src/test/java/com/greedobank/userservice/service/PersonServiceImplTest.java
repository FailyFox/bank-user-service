package com.greedobank.userservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.greedobank.userservice.BaseTest;
import com.greedobank.userservice.model.Manager;
import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.repository.PersonRepository;
import com.greedobank.userservice.security.AuthRequest;
import com.greedobank.userservice.security.AuthResponse;
import com.greedobank.userservice.service.impl.PersonServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest extends BaseTest {

  @Mock
  private PersonRepository personRepository;
  @InjectMocks
  private PersonServiceImpl personService;

  private Person person;

  private AuthRequest request;
  private AuthResponse response;

  @BeforeEach
  public void setup() {
    person = createPerson();
    request = createAuthRequest();
  }

  @Test
  public void getPersonByEmail_thenReturnPerson() {
    when(personRepository.getPersonByEmail("tchornyi@gmail.com")).thenReturn(Optional.of(person));
    Person personResponse = personService.getPersonByEmail("tchornyi@gmail.com");

    Assertions.assertEquals(personResponse.getId(), person.getId());
    Assertions.assertEquals(personResponse.getFname(), person.getFname());
    Assertions.assertEquals(personResponse.getLname(), person.getLname());
    Assertions.assertEquals(personResponse.getEmail(), person.getEmail());
    Assertions.assertEquals(personResponse.getAddress(), person.getAddress());
  }
}