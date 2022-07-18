package com.greedobank.userservice.mapper.request;

import com.greedobank.userservice.dto.request.CustomerUpdateStatusRequestDto;
import com.greedobank.userservice.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerUpdateStatusRequestMapper {

  Customer updateStatus(CustomerUpdateStatusRequestDto customerUpdateStatusRequestDto,
      @MappingTarget Customer customer);
}