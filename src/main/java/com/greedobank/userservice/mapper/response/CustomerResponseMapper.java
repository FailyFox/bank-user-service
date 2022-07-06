package com.greedobank.userservice.mapper.response;

import com.greedobank.userservice.dto.response.CustomerResponseDto;
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
  CustomerResponseDto toDto(Customer customer);

  Customer toCustomer(CustomerResponseDto customerResponseDto);
}