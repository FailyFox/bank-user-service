package com.greedobank.userservice.service.impl;

import com.greedobank.userservice.dto.request.CustomerDtoRequest;
import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.mapper.request.CustomerMapperRequest;
import com.greedobank.userservice.mapper.response.CustomerMapperResponse;
import com.greedobank.userservice.model.Customer;
import com.greedobank.userservice.repository.CustomerRepository;
import com.greedobank.userservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapperRequest customerMapperRequest;
  private final CustomerMapperResponse customerMapperResponse;

  public CustomerDtoResponse getCustomer(int id) {
    return customerRepository.findById(id)
        .map(customerMapperResponse::toDto)
        .orElseThrow(() -> new EntityNotFoundException("customer", id));
  }

  public CustomerDtoResponse addCustomer(CustomerDtoRequest dtoCustomer) {
    Customer customer = customerMapperRequest.toCustomer(dtoCustomer);
    return customerMapperResponse.toDto(customerRepository.save(customer));
  }
}