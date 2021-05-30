package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
//using name as the key in table, so we put String in CrudRepository<Person, String>
//public interface UserRepository extends CrudRepository<Person, String> {
public interface UserRepository extends JpaRepository<Person, String> {

}
