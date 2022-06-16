package com.greedobank.userservice.repository;

import com.greedobank.userservice.model.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}