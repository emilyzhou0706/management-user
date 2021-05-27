package com.example.usermanagement.controller;

import com.example.usermanagement.*;
import com.example.usermanagement.constant.Status;
import com.example.usermanagement.entity.*;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.GetPersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        Person person = getPersonServiceImpl.getPerson(profileReqAdd);
        userRepository.save(person);
    }


}