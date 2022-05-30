package com.greedobank.userservice.mapper.response;

import com.greedobank.userservice.dto.response.CustomerDtoResponse;
import com.greedobank.userservice.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapperResponse {
    @Mappings({
            @Mapping(target = "fname", source = "customer.person.fname"),
            @Mapping(target = "lname", source = "customer.person.lname"),
            @Mapping(target = "email", source = "customer.person.email"),
            @Mapping(target = "address", source = "customer.person.address")
    })
    CustomerDtoResponse toDto(Customer customer);
    Customer toCustomer(CustomerDtoResponse customerDtoResponse);
}