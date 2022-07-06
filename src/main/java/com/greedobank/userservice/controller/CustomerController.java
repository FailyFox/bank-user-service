package com.greedobank.userservice.controller;

import com.greedobank.userservice.dto.request.CustomerDtoRequest;
import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.service.CustomerService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/{id}")
  @Secured({"ROLE_MANAGER"})
  public CustomerDtoResponse getCustomer(@PathVariable Integer id) {
    return customerService.getCustomer(id);
  }

  @GetMapping
  @Secured({"ROLE_MANAGER"})
  public List<CustomerDtoResponse> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @PostMapping
  @Secured({"ROLE_CUSTOMER"})
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerDtoResponse addCustomer(@Valid @RequestBody CustomerDtoRequest customer) {
    return customerService.addCustomer(customer);
  }
}