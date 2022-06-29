package com.greedobank.userservice.service;

import com.greedobank.userservice.dto.response.CustomerResponseDto;

public interface CustomerService {

  CustomerResponseDto getCustomer(int id);
}