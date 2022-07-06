package com.greedobank.userservice.service;

import com.greedobank.userservice.dto.request.CustomerDtoRequest;
import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import java.util.List;

public interface CustomerService {

  CustomerDtoResponse getCustomer(int id);

  List<CustomerDtoResponse> getAllCustomers();

  CustomerDtoResponse addCustomer(CustomerDtoRequest dtoCustomer);
}