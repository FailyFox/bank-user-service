package com.greedobank.userservice.repository;

import com.greedobank.userservice.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  List<Customer> findAll();
}