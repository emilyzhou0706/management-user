package com.example.usermanagement;

import com.example.usermanagement.entity.*;
import com.example.usermanagement.service.GetPersonServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
/*import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;*/
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

public class GetPersonServiceImplTest {
    @Mock
    RestTemplate restTemplate;

    private GetPersonServiceImpl getPersonServiceImpl;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        /*getPersonServiceImpl=new GetPersonServiceImpl();
        ProfileReqAdd profileReqAdd=new ProfileReqAdd();
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        profileReqAdd.setTag("tag");*/
    }

    @Test
    public void shouldReturnPerson_whenGetPersonIsCalled(){
        getPersonServiceImpl=new GetPersonServiceImpl();
        ProfileReqAdd profileReqAdd=new ProfileReqAdd();
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        profileReqAdd.setTag("tag");
        extracted(profileReqAdd);
        Person person=getPersonServiceImpl.getPerson(profileReqAdd);
    }

    private void extracted(ProfileReqAdd profileReqAdd) {
        GuessAge guessAge= new GuessAge();
        guessAge.setAge(1);
        guessAge.setCount(123);
        guessAge.setName("tony");

        doReturn(guessAge).when(restTemplate).getForObject("https://api.agify.io/?name={nameE}", GuessAge.class,profileReqAdd.getFirstName());

        GuessGender guessGender=new GuessGender();
        guessGender.setGender("male");
        guessGender.setName("tony");
        guessGender.setProbability(12344556676l);

        doReturn(guessGender).when(restTemplate).getForObject("https://api.genderize.io/?name={nameE}", GuessGender.class,profileReqAdd.getFirstName());

        GuessNation guessNation=new GuessNation();
        guessNation.setName("tony");
        List<Country> countryList=new ArrayList<>();
        countryList.add(new Country("LB",0.001d));
        countryList.add(new Country("JE",0.002d));
        guessNation.setCountry(countryList);

        doReturn(guessNation).when(restTemplate).getForObject("https://api.nationalize.io/?name={nameE}", GuessNation.class,profileReqAdd.getFirstName());
    }

}