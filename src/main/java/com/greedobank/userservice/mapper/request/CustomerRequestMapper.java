package com.greedobank.userservice.mapper.request;

import com.greedobank.userservice.dto.request.CustomerDtoRequest;
import com.greedobank.userservice.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

  CustomerDtoRequest toDto(Customer customer);

  Customer toCustomer(CustomerDtoRequest customerRequestDto);
}