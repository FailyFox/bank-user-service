package com.greedobank.userservice;

import com.greedobank.userservice.dto.request.CustomerRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.model.Customer;
import com.greedobank.userservice.model.Manager;
import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.security.AuthRequest;
import java.time.LocalDate;

public abstract class BaseTest {
  protected static final Integer ID_DEFAULT = 1;
  protected static final Integer ID_ZERO = 0;
  protected static final LocalDate birthday = LocalDate.of(1988, 9,30);
  protected static final String PASSWORD = "$2a$10$KGCOzcEEF2GsWpjBCkxziulQyPq0uXMjM19gf10PHHnKQoJRLS6ji";

  protected static Person createPersonCustomer() {
    return Person.builder()
        .id(1)
        .fname("Taras")
        .lname("Chornyi")
        .email("tchornyi@gmail.com")
        .password("$2a$10$KGCOzcEEF2GsWpjBCkxziulQyPq0uXMjM19gf10PHHnKQoJRLS6ji")
        .address("Pidbitrsi, Trakt Glyn`anskyi 4")
        .customer(createCustomer())
        .build();
  }

  protected static Person createPersonManager() {
    return Person.builder()
        .id(1)
        .fname("Roman")
        .lname("Zherebetskyi")
        .email("rzherebetskyi@gmail.com")
        .password("rzherebetskyi")
        .address("Lviv, Pidmurna 5a")
        .manager(createManager())
        .build();
  }

  protected static Person createUnauthorizedPerson() {
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
        .build();
  }

  protected static Manager createManager() {
    return Manager.builder()
        .id(1)
import com.greedobank.userservice.dto.request.CustomerUpdateStatusRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.model.enums.Status;
import java.time.LocalDate;

public abstract class BaseTest {

  protected static final Integer ID_DEFAULT = 1;
  protected static final LocalDate BIRTHDAY = LocalDate.of(1988,9,30);

  protected static CustomerUpdateStatusRequestDto validUpdateCustomerStatus() {
    return CustomerUpdateStatusRequestDto.builder()
        .status(Status.APPROVED)
        .build();
  }

  protected static CustomerUpdateStatusRequestDto invalidUpdateCustomerStatus() {
    return CustomerUpdateStatusRequestDto.builder()
        .status(null)
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
        .birthday(BIRTHDAY)
        .status(Status.APPROVED)
        .birthday(birthday)
        .build();
  }

  protected static CustomerRequestDto createValidCustomerRequestDto() {
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

  protected static CustomerRequestDto createInvalidCustomerRequestDto() {
    return CustomerRequestDto.builder()
        .fname(null)
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