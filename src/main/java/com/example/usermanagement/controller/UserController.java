package com.example.usermanagement.controller;

import com.example.usermanagement.*;
import com.example.usermanagement.entity.*;
import com.example.usermanagement.exception.ResourceNotFoundException;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.GetPersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-management")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserManagementApplication.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GetPersonServiceImpl getPersonServiceImpl;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void postBody(@RequestBody ProfileReqAdd profileReqAdd) {

        Person person = getPersonServiceImpl.getPersonByApi(profileReqAdd);
        userRepository.save(person);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Person> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Person getOne(@PathVariable String id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String userId) throws Exception {
        Person person =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        userRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Person> updateUser(
            @PathVariable(value = "id") String userId, @Valid @RequestBody ProfileReqAdd profileReqAdd)
            throws ResourceNotFoundException {

        Person person =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

        person.setTag(profileReqAdd.getTag().stream()
                .collect(Collectors.joining(":")));
        person.setPassword(profileReqAdd.getPassword());
        person.setLastName(profileReqAdd.getLastName());
        person.setEmail(profileReqAdd.getEmail());
        person.setFirstName(profileReqAdd.getFirstName());
        person.setContactNumber(profileReqAdd.getContactNumber());

        final Person updatedUser = userRepository.save(person);
        return ResponseEntity.ok(updatedUser);
    }

}