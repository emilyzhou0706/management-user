package com.example.usermanagement;

import com.example.usermanagement.constant.Status;
import com.example.usermanagement.controller.UserController;
import com.example.usermanagement.entity.*;
import com.example.usermanagement.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    private static final Logger log = LoggerFactory.getLogger(UserManagementApplication.class);

    @Mock
    RestTemplate restTemplate;

    @MockBean
    UserRepository userRepository;

    @InjectMocks
    UserController userController;

    @Test
    public void testPostBody(){
        ProfileReqAdd profileReqAdd=new ProfileReqAdd();
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        profileReqAdd.setTag("tag");

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

        Person person= new Person();
        person.setAge(1);
        person.setContactNumber("12344");
        person.setEmail("testemail@gmail.com");
        person.setGender("male");
        person.setFirstName("tony");
        person.setPassword("123456");
        person.setLastName("albert");
        person.setTag("tag");
        person.setPassword("123456");
        person.setNationality("LB");
        doReturn(person).when(userRepository).save(person);
        userController.postBody(profileReqAdd);

    }


}
