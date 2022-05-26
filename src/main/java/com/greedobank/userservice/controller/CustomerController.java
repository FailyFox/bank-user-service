package com.greedobank.userservice.controller;

import com.greedobank.userservice.dto.request.CustomerDtoRequest;
import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @GetMapping("/{id}")
    public CustomerDtoResponse getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public CustomerDtoResponse addCustomer(@RequestBody CustomerDtoRequest customer) {
        return customerService.addCustomer(customer);
    }
}