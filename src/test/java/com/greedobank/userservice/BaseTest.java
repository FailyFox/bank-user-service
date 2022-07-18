package com.greedobank.userservice;

import com.greedobank.userservice.dto.request.CustomerUpdateStatusRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import java.time.LocalDate;

public abstract class BaseTest {

  protected static final Integer ID_DEFAULT = 1;
  protected static final LocalDate BIRTHDAY = LocalDate.of(1988,9,30);

  protected static CustomerUpdateStatusRequestDto validUpdateCustomerStatus() {
    return CustomerUpdateStatusRequestDto.builder()
        .status("APPROVED")
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
        .status("APPROVED")
        .build();
  }
}