package com.greedobank.userservice.repository;

import com.greedobank.userservice.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}