package com.greedobank.userservice.repository;

import com.greedobank.userservice.model.Person;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

  Optional<Person> getPersonByEmail(String email);
}