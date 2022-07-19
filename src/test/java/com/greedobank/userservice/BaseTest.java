package com.greedobank.userservice;

import com.greedobank.userservice.dto.request.CustomerRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.model.Customer;
import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.security.AuthRequest;
import com.greedobank.userservice.security.AuthResponse;
import java.time.LocalDate;

public class BaseTest {
  protected static final Integer ID_DEFAULT = 1;
  protected static final LocalDate birthday = LocalDate.of(1988, 9,30);

  protected static Person createPerson() {
    return Person.builder()
        .id(1)
        .fname("Taras")
        .lname("Chornyi")
        .email("tchornyi@gmail.com")
        .password("password")
        .address("Pidbitrsi, Trakt Glyn`anskyi 4")
        .build();
  }

  protected static Customer createCustomer() {
    return Customer.builder()
        .id(1)
        .phone("+3806735487127")
        .idCode("idCode")
        .passportData("passport")
        .birthday(birthday)
        .person(createPerson())
        .build();
  }

  protected static CustomerResponseDto createCustomerResponseDto() {
    return CustomerResponseDto.builder()
        .id(1)
        .fname("Taras")
        .lname("Chornyi")
        .email("tchornyi@gmail.com")
        .address("Pidbitrsi, Trakt Glyn`anskyi 4")
        .phone("+380674567612")
        .idCode("3456317822")
        .passportData("3267843678843")
        .birthday(birthday)
        .build();
  }

  protected static CustomerRequestDto createCustomerRequestDto() {
    return CustomerRequestDto.builder()
        .fname("Taras")
        .lname("Chornyi")
        .email("tchornyi@gmail.com")
        .address("Pidbitrsi, Trakt Glyn`anskyi 4")
        .password("password")
        .phone("+380674567612")
        .idCode("3456317822")
        .passportData("3267843678843")
        .build();
  }

  protected static AuthRequest createAuthRequest() {
    return AuthRequest.builder()
        .email("tchornyi@gmail.com")
        .password("$2a$10$KGCOzcEEF2GsWpjBCkxziulQyPq0uXMjM19gf10PHHnKQoJRLS6ji")
        .build();
  }
}