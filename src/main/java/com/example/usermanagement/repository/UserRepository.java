package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Person, Integer> {
}
