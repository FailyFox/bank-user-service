package com.greedobank.userservice.service;

import com.greedobank.userservice.dto.request.CustomerRequestDto;
import com.greedobank.userservice.dto.request.CustomerUpdateStatusRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.model.Person;
import java.util.List;

public interface CustomerService {

  CustomerResponseDto getCustomer(int id);

  List<CustomerResponseDto> getAllCustomers();

  CustomerResponseDto addCustomer(CustomerRequestDto dtoCustomer);

  CustomerResponseDto updateCustomerStatus(CustomerUpdateStatusRequestDto dto, Integer id);
}