package com.greedobank.userservice.repository;

import com.greedobank.userservice.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

  Optional<Person> getPersonByEmail(String email);
}