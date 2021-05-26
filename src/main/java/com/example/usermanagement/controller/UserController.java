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
/*    @Autowired
    RestTemplate restTemplate;*/

    @Autowired
    private UserRepository userRepository;

    @Autowired
    GetPersonServiceImpl getPersonServiceImpl;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void postBody(@RequestBody ProfileReqAdd profileReqAdd) {

//        Person person = getPerson(profileReqAdd);
        Person person = getPersonServiceImpl.getPerson(profileReqAdd);
        userRepository.save(person);
    }

    /*public Person getPerson(ProfileReqAdd profileReqAdd) {
        GuessAge guessAge =restTemplate.getForObject("https://api.agify.io/?name={nameE}", GuessAge.class, profileReqAdd.getFirstName());
        log.info(guessAge.toString());

        GuessGender guessGender =restTemplate.getForObject("https://api.genderize.io/?name={nameE}", GuessGender.class, profileReqAdd.getFirstName());
        log.info(guessGender.toString());

        GuessNation guessNation =restTemplate.getForObject("https://api.nationalize.io/?name={nameE}", GuessNation.class, profileReqAdd.getFirstName());
        log.info(guessNation.toString());

        guessNation.getCountry().sort(
                (Country h1, Country h2) -> -h1.getProbability().compareTo(h2.getProbability()));

        String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
        String dateString = simpleDateFormat.format(new Date());

        Person person= new Person(profileReqAdd.getEmail(), profileReqAdd.getPassword(),guessAge.getName(), profileReqAdd.getLastName(), profileReqAdd.getEmail(), profileReqAdd.getContactNumber(),guessAge.getAge(),guessGender.getGender(),guessNation.getCountry().get(0).getCountry_id(), profileReqAdd.getTag(), Status.active.name(),dateString,dateString);
        return person;
    }
*/

}