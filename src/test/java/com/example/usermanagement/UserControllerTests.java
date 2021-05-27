/*
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//it's for the code that we don't have serviceImpl yet
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    private static final Logger log = LoggerFactory.getLogger(UserManagementApplication.class);

    @Mock
    RestTemplate restTemplate;

    @Mock
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
//        profileReqAdd.setTag("tag");
        List tag=new ArrayList();
        tag.add("a");
        tag.add("b");
        tag.add("c");
        profileReqAdd.setTag(tag);

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

        Person person1= new Person();
        Person person2= new Person();
        person1.setAge(1);
        person1.setContactNumber("9876654f31");
        person1.setEmail("testemail@gmail.com");
        person1.setUsername("testemail@gmail.com");
        person1.setGender("male");
        person1.setFirstName("tony");
        person1.setPassword("123456");
        person1.setLastName("albert");
        person1.setTag("tag");
        person1.setPassword("123456");
        person1.setNationality("JE");
        person1.setStatus("active");
        doReturn(person2).when(userRepository).save(person1);
        //doReturn(person1).when(userRepository).save(person2);
//        String result = userController.postBody(profileReqAdd);
        verify(userController,times(1)).postBody(profileReqAdd);
        verify(userRepository, times(1)).save(person1);
//        assertEquals("201 Created", result);

    }


}
*/
