package com.greedobank.userservice.controller;

import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.service.impl.CustomerServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

  private final CustomerServiceImpl customerService;

  @GetMapping
  @Secured({"ROLE_MANAGER"})
  public List<CustomerDtoResponse> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/{id}")
  @Secured({"ROLE_MANAGER"})
  public CustomerDtoResponse getCustomer(@PathVariable Integer id) {
    return customerService.getCustomer(id);
  }
}