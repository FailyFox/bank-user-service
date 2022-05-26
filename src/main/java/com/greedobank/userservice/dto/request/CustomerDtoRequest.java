package com.greedobank.userservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDtoRequest {
    private String fname;
    private String lname;
    private String email;
    private String address;
    private String phone;
    private String idCode;
    private String passportData;
    private LocalDate birthday;
}