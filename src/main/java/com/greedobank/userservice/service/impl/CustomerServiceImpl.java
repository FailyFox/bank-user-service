package com.greedobank.userservice.service.impl;

import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.mapper.response.CustomerResponseMapper;
import com.greedobank.userservice.repository.CustomerRepository;
import com.greedobank.userservice.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerResponseMapper customerMapperResponse;

  public List<CustomerResponseDto> getAllCustomers() {
    return customerRepository.findAll()
        .stream()
        .map(customerMapperResponse::toDto)
        .collect(Collectors.toList());
  }

  public CustomerResponseDto getCustomer(int id) {
    return customerRepository.findById(id)
        .map(customerMapperResponse::toDto)
        .orElseThrow(() -> new EntityNotFoundException("Customer", id));
  }
}