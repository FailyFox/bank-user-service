package com.greedobank.userservice.mapper.request;

import com.greedobank.userservice.dto.request.CustomerRequestDto;
import com.greedobank.userservice.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

  CustomerRequestDto toDto(Customer customer);

  Customer toCustomer(CustomerRequestDto customerRequestDto);
}