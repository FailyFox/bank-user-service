package com.greedobank.userservice.service.impl;

import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.mapper.response.CustomerResponseMapper;
import com.greedobank.userservice.repository.CustomerRepository;
import com.greedobank.userservice.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerResponseMapper customerResponseMapper;

  public List<CustomerDtoResponse> getAllCustomers() {
    List<CustomerDtoResponse> customers = new ArrayList<>();
    customerRepository.findAll()
        .stream()
        .map(customerResponseMapper::toDto)
        .forEach(customers::add);
    return customers;
  }

  public CustomerDtoResponse getCustomer(int id) {
    return customerRepository.findById(id)
        .map(customerResponseMapper::toDto)
        .orElseThrow(() -> new EntityNotFoundException("customer", id));
  }
}