package com.greedobank.userservice.repository;

import com.greedobank.userservice.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}