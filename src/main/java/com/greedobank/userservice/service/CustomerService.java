package com.greedobank.userservice.service;

import com.greedobank.userservice.dto.request.CustomerDtoRequest;
import com.greedobank.userservice.dto.response.CustomerDtoResponse;

public interface CustomerService {
    CustomerDtoResponse getCustomer(int id);
    CustomerDtoResponse addCustomer(CustomerDtoRequest customerDtoRequest);
}