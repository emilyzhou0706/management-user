package com.example.usermanagement.service;

import com.example.usermanagement.UserManagementApplication;
import com.example.usermanagement.constant.Status;
import com.example.usermanagement.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

@Service("getPersonServiceImpl")
public class GetPersonServiceImpl implements GetPersonService{
    private static final Logger log = LoggerFactory.getLogger(UserManagementApplication.class);

    @Autowired
    RestTemplate restTemplate;

    public GetPersonServiceImpl() {
    }

    public GetPersonServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Person getPersonByApi(ProfileReqAdd profileReqAdd) {
        GuessAge guessAge =restTemplate.getForObject("https://api.agify.io/?name={nameE}", GuessAge.class, profileReqAdd.getFirstName());
        log.info(guessAge.toString());

        GuessGender guessGender =restTemplate.getForObject("https://api.genderize.io/?name={nameE}", GuessGender.class, profileReqAdd.getFirstName());
        log.info(guessGender.toString());

        GuessNation guessNation =restTemplate.getForObject("https://api.nationalize.io/?name={nameE}", GuessNation.class, profileReqAdd.getFirstName());
        log.info(guessNation.toString());

        guessNation.getCountry().sort(
                (h1, h2) -> -h1.getProbability().compareTo(h2.getProbability()));

        String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
        String dateString = simpleDateFormat.format(new Date());

        String tagResult= profileReqAdd.getTag().stream()
                .collect(Collectors.joining(":"));
        Person person= new Person(profileReqAdd.getEmail(), profileReqAdd.getPassword(),guessAge.getName(), profileReqAdd.getLastName(), profileReqAdd.getEmail(), profileReqAdd.getContactNumber(),guessAge.getAge(),guessGender.getGender(),guessNation.getCountry().get(0).getCountry_id(), tagResult, Status.active.name(),dateString,dateString);
        return person;
    }
}
