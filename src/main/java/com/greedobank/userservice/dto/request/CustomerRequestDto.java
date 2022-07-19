package com.greedobank.userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

  @NotNull(message = "Name is missing")
  @Size(min = 3, max = 45, message = "Name should be from 3 to 45 letters")
  private String fname;

  @NotNull(message = "Surname is missing")
  @Size(min = 3, max = 45, message = "Surname should be from 3 to 45 letters")
  private String lname;

  @NotBlank(message = "Email is missing")
  @Email(message = "Email is not valid")
  private String email;

  @NotBlank(message = "Address is missing")
  private String address;

  @NotNull(message = "Password is missing")
  @NotBlank(message = "Password is empty")
  private String password;

  @NotNull(message = "Phone is missing")
  @Pattern(regexp = "(^\\+[0-9]{3,16}$)|(^[0-9]{1,4}$)",
      message = "Wrong phone number format. Should be like +000000000000")
  private String phone;

  @NotBlank(message = "ID code is missing")
  private String idCode;

  @NotBlank(message = "Passport data is missing")
  private String passportData;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;
}