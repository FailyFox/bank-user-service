package com.greedobank.userservice.service;

import com.greedobank.userservice.dto.request.CustomerDtoRequest;
import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.exception.NoSuchCustomerException;
import com.greedobank.userservice.mapper.request.CustomerMapperRequest;
import com.greedobank.userservice.mapper.response.CustomerMapperResponse;
import com.greedobank.userservice.model.Customer;
import com.greedobank.userservice.repository.CustomerRepository;
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
                .orElseThrow(() -> new NoSuchCustomerException("There is no customer with that id"));
    }

    public CustomerDtoResponse addCustomer(CustomerDtoRequest dtoCustomer) {
        Customer customer = customerMapperRequest.toCustomer(dtoCustomer);
        return customerMapperResponse.toDto(customerRepository.save(customer));
    }
}