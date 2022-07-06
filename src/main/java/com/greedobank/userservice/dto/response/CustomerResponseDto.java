package com.greedobank.userservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {

  private Integer id;
  private String fname;
  private String lname;
  private String email;
  private String address;
  private String phone;
  private String idCode;
  private String passportData;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;
}