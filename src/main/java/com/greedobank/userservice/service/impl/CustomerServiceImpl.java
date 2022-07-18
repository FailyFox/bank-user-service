package com.greedobank.userservice.service.impl;

import static com.greedobank.userservice.util.Constants.ENTITY_CUSTOMER;

import com.greedobank.userservice.dto.request.CustomerRequestDto;
import com.greedobank.userservice.dto.request.CustomerUpdateStatusRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.mapper.request.CustomerRequestMapper;
import com.greedobank.userservice.mapper.request.CustomerUpdateStatusRequestMapper;
import com.greedobank.userservice.mapper.response.CustomerResponseMapper;
import com.greedobank.userservice.model.Customer;
import com.greedobank.userservice.model.Person;
import com.greedobank.userservice.repository.CustomerRepository;
import com.greedobank.userservice.repository.PersonRepository;
import com.greedobank.userservice.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerResponseMapper customerResponseMapper;
  private final CustomerRequestMapper customerRequestMapper;
  private final PersonRepository personRepository;

  @Override
  public CustomerResponseDto getCustomer(int id) {
    return customerRepository.findById(id)
        .map(customerResponseMapper::toDto)
        .orElseThrow(() -> new EntityNotFoundException("customer", id));
  }

  @Override
  public List<CustomerResponseDto> getAllCustomers() {
    return customerRepository.findAll()
        .stream()
        .map(customerResponseMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public CustomerResponseDto addCustomer(CustomerRequestDto dtoCustomer) {
    Person person = createPerson(dtoCustomer);
    personRepository.save(person);
    Customer customer = customerRequestMapper.toCustomer(dtoCustomer);
    customer.setStatus("PENDING");
    person.setCustomer(customer);
    customer.setPerson(person);
    return customerResponseMapper.toDto(customerRepository.save(customer));
  }

  @Override
  public CustomerResponseDto updateCustomerStatus(CustomerUpdateStatusRequestDto dto, Integer id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(ENTITY_CUSTOMER, id));
    customer.setStatus(dto.getStatus());
    return customerResponseMapper.toDto(customerRepository.save(customer));
  }

  private Person createPerson(CustomerRequestDto dtoCustomer) {
    Person person = new Person();
    person.setFname(dtoCustomer.getFname());
    person.setLname(dtoCustomer.getLname());
    person.setEmail(dtoCustomer.getEmail());
    person.setPassword(dtoCustomer.getPassword());
    person.setAddress(dtoCustomer.getAddress());
    return person;
  }
}