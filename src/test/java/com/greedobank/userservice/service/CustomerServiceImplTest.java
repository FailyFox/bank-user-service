package com.greedobank.userservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.greedobank.userservice.BaseTest;
import com.greedobank.userservice.dto.request.CustomerRequestDto;
import com.greedobank.userservice.dto.response.CustomerResponseDto;
import com.greedobank.userservice.exception.EntityNotFoundException;
import com.greedobank.userservice.mapper.request.CustomerRequestMapper;
import com.greedobank.userservice.mapper.response.CustomerResponseMapper;
import com.greedobank.userservice.model.Customer;
import com.greedobank.userservice.repository.CustomerRepository;
import com.greedobank.userservice.repository.PersonRepository;
import com.greedobank.userservice.service.impl.CustomerServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest extends BaseTest {

  @Mock
  private CustomerRepository customerRepository;
  @Mock
  private PersonRepository personRepository;
  @Mock
  private CustomerResponseMapper customerResponseMapper;
  @Mock
  private CustomerRequestMapper customerRequestMapper;
  @InjectMocks
  private CustomerServiceImpl customerService;

  private Customer customer;
  private CustomerRequestDto customerRequestDto;
  private CustomerResponseDto customerResponseDto;

  @BeforeEach
  public void setup() {
    customer = createCustomer();
    customerRequestDto = createValidCustomerRequestDto();
    customerResponseDto = createCustomerResponseDto();
  }

  @Test
  public void getCustomerAccountById_thenReturnResponseDto() {
    when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
    when(customerResponseMapper.toDto(any(Customer.class))).thenReturn(customerResponseDto);
    CustomerResponseDto responseDto = customerService.getCustomer(ID_DEFAULT);
    Assertions.assertEquals(responseDto.getId(), responseDto.getId());
    Assertions.assertEquals(responseDto.getFname(), responseDto.getFname());
    Assertions.assertEquals(responseDto.getLname(), responseDto.getLname());
    Assertions.assertEquals(responseDto.getEmail(), responseDto.getEmail());
    Assertions.assertEquals(responseDto.getAddress(), responseDto.getAddress());
    Assertions.assertEquals(responseDto.getPhone(), responseDto.getPhone());
    Assertions.assertEquals(responseDto.getIdCode(), responseDto.getIdCode());
    Assertions.assertEquals(responseDto.getPassportData(), responseDto.getPassportData());
    Assertions.assertEquals(responseDto.getBirthday(), responseDto.getBirthday());
  }

  @Test
  public void getCustomerAccountById_thenReturnEntityNotFoundException() {
    when(customerRepository.findById(ID_DEFAULT)).thenReturn(Optional.empty());
    EntityNotFoundException exception = Assert.assertThrows(EntityNotFoundException.class,
        () -> customerService.getCustomer(ID_DEFAULT));
    Assertions.assertEquals(exception.getMessage(), "There is no customer with id 1");
  }

  @Test
  public void getAllCustomerAccounts_thenReturnResponseDto() {
    when(customerRepository.findAll()).thenReturn(List.of(customer));
    when(customerResponseMapper.toDto(any(Customer.class))).thenReturn(customerResponseDto);
    List<CustomerResponseDto> responseDto = customerService.getAllCustomers();
    Assertions.assertEquals(responseDto.get(0).getId(), responseDto.get(0).getId());
    Assertions.assertEquals(responseDto.get(0).getFname(), responseDto.get(0).getFname());
    Assertions.assertEquals(responseDto.get(0).getLname(), responseDto.get(0).getLname());
    Assertions.assertEquals(responseDto.get(0).getEmail(), responseDto.get(0).getEmail());
    Assertions.assertEquals(responseDto.get(0).getAddress(), responseDto.get(0).getAddress());
    Assertions.assertEquals(responseDto.get(0).getPhone(), responseDto.get(0).getPhone());
    Assertions.assertEquals(responseDto.get(0).getIdCode(), responseDto.get(0).getIdCode());
    Assertions.assertEquals(responseDto.get(0).getPassportData(), responseDto.get(0).getPassportData());
    Assertions.assertEquals(responseDto.get(0).getBirthday(), responseDto.get(0).getBirthday());
  }

  @Test
  public void createCustomerAccount_thenReturnResponseDto() {
    when(customerRequestMapper.toCustomer(customerRequestDto)).thenReturn(customer);
    when(customerRepository.save(any(Customer.class))).thenReturn(customer);
    when(customerResponseMapper.toDto(any(Customer.class))).thenReturn(customerResponseDto);
    CustomerResponseDto responseDto = customerService.addCustomer(customerRequestDto);
    Assertions.assertEquals(responseDto.getId(), responseDto.getId());
    Assertions.assertEquals(responseDto.getFname(), responseDto.getFname());
    Assertions.assertEquals(responseDto.getLname(), responseDto.getLname());
    Assertions.assertEquals(responseDto.getEmail(), responseDto.getEmail());
    Assertions.assertEquals(responseDto.getAddress(), responseDto.getAddress());
    Assertions.assertEquals(responseDto.getPhone(), responseDto.getPhone());
    Assertions.assertEquals(responseDto.getIdCode(), responseDto.getIdCode());
    Assertions.assertEquals(responseDto.getPassportData(), responseDto.getPassportData());
    Assertions.assertEquals(responseDto.getBirthday(), responseDto.getBirthday());
  }
}