package com.greedobank.userservice.mapper.response;

import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

  @Mappings({
      @Mapping(target = "fname", source = "person.fname"),
      @Mapping(target = "lname", source = "person.lname"),
      @Mapping(target = "email", source = "person.email"),
      @Mapping(target = "address", source = "person.address")
  })
  CustomerDtoResponse toDto(Customer customer);

  Customer toCustomer(CustomerDtoResponse customerDtoResponse);
}