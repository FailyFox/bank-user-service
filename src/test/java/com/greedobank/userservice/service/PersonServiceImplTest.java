package com.greedobank.userservice.service;

import static org.mockito.Mockito.when;

import com.greedobank.userservice.BaseTest;
import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.repository.PersonRepository;
import com.greedobank.userservice.security.AuthRequest;
import com.greedobank.userservice.security.AuthResponse;
import com.greedobank.userservice.security.details.PersonDetails;
import com.greedobank.userservice.security.jwt.JwtProvider;
import com.greedobank.userservice.service.impl.PersonServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest extends BaseTest {

  @Mock
  private PersonRepository personRepository;
  @Mock
  private JwtProvider jwtProvider;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private PersonServiceImpl personService;

  private Person personCustomer;
  private Person personManager;
  private Person personUnauthorized;
  private AuthRequest request;
  private AuthResponse response;
  private PersonDetails personDetails;

  @BeforeEach
  public void setup() {
    personCustomer = createPersonCustomer();
    personManager = createPersonManager();
    personUnauthorized = createUnauthorizedPerson();
    request = createAuthRequest();
  }

  @Test
  public void authentication_thenReturnAuthResponse() {
    String token = jwtProvider.generateToken(personCustomer.getEmail());
    new AuthResponse(token);
//    when()
//    when(personRepository.getPersonByEmail("tchornyi@gmail.com"))
//        .thenReturn(Optional.of(personCustomer));
//    String token = jwtProvider.generateToken(personCustomer.getEmail());
//    AuthResponse response = personService.authentication(request);
//    //Assertions.assertEquals(response,pe);
  }

  @Test
  public void getPersonByEmail_thenReturnPerson() {
    when(personRepository.getPersonByEmail("tchornyi@gmail.com"))
        .thenReturn(Optional.of(personCustomer));
    Person personResponse = personService.getPersonByEmail("tchornyi@gmail.com");
    Assertions.assertEquals(personResponse.getId(), personCustomer.getId());
    Assertions.assertEquals(personResponse.getFname(), personCustomer.getFname());
    Assertions.assertEquals(personResponse.getLname(), personCustomer.getLname());
    Assertions.assertEquals(personResponse.getEmail(), personCustomer.getEmail());
    Assertions.assertEquals(personResponse.getAddress(), personCustomer.getAddress());
  }

  @Test
  public void getPersonByEmailAndPassword_thenReturnPerson() {
    when(personRepository.getPersonByEmail("tchornyi@gmail.com"))
        .thenReturn(Optional.of(personCustomer));
    Person personResponse = personService.getPersonByEmail("tchornyi@gmail.com");
    Boolean encoder = passwordEncoder
        .matches(personCustomer.getPassword(), "tchornyi");
    Assertions.assertEquals(PASSWORD, personCustomer.getPassword());
//    Assertions.assertEquals(personResponse.getId(), personCustomer.getId());
//    Assertions.assertEquals(personResponse.getFname(), personCustomer.getFname());
//    Assertions.assertEquals(personResponse.getLname(), personCustomer.getLname());
//    Assertions.assertEquals(personResponse.getEmail(), personCustomer.getEmail());
//    Assertions.assertEquals(personResponse.getAddress(), personCustomer.getAddress());
  }

  @Test
  public void getPersonRole_thenReturnRoleCustomer() {
    String role = personService.getRole(personCustomer);
    Assertions.assertEquals(role, "ROLE_CUSTOMER");
  }

  @Test
  public void getPersonRole_thenReturnRoleManager() {
    String role = personService.getRole(personManager);
    Assertions.assertEquals(role, "ROLE_MANAGER");
  }

  @Test
  public void getPersonRole_thenReturnUnauthorizedPerson() {
    String role = personService.getRole(personUnauthorized);
    Assertions.assertEquals(role, "Unauthorized Person");
  }

  @Test
  public void getCustomerAccountById_thenReturnUnauthorized() throws Exception {
    //personDetailsService.loadUserByUsername(null);
    //when(customerService.getCustomer(anyInt())).thenReturn(responseDto);
    //when(personDetailsService.loadUserByUsername(null)).thenReturn(null);
    personDetails = null;

  }
}