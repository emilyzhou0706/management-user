/*
package com.example.usermanagement;

import com.example.usermanagement.controller.UserController;
import com.example.usermanagement.entity.*;
import com.example.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests2 {
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
        profileReqAdd.setTag("tag");
    */
/*    Person person = getPerson(profileReqAdd);
        doReturn(person2).when(userRepository).save(person1);*//*


        extracted(profileReqAdd);
        userController.postBody(profileReqAdd);
//        verify(userController, times(1)).postBody(profileReqAdd);

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

    @Test
    public void testGetPersonHappyPath() {

        ProfileReqAdd profileReqAdd=new ProfileReqAdd();
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        profileReqAdd.setTag("tag");

        extracted(profileReqAdd);

        Person person=userController.getPerson(profileReqAdd);
        assertEquals(1,person.getAge());
        assertEquals("testemail@gmail.com",person.getEmail());
        assertEquals("9876654f31",person.getContactNumber());
        assertEquals("male",person.getGender());
        assertEquals("tony",person.getFirstName());
        assertEquals("albert",person.getLastName());
        assertEquals("123456",person.getPassword());
        assertEquals("tag",person.getTag());
        assertEquals("JE",person.getNationality());
        assertEquals("active",person.getStatus());
        assertEquals("testemail@gmail.com",person.getUsername());
    }

    @Test
    public void testGetPersonNullEmail() {

        ProfileReqAdd profileReqAdd=new ProfileReqAdd();
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("");
        profileReqAdd.setContactNumber("9876654f31");
        profileReqAdd.setTag("tag");

        extracted(profileReqAdd);

        Person person=userController.getPerson(profileReqAdd);
        assertEquals(1,person.getAge());
        assertEquals("",person.getEmail());
        assertEquals("9876654f31",person.getContactNumber());
        assertEquals("male",person.getGender());
        assertEquals("tony",person.getFirstName());
        assertEquals("albert",person.getLastName());
        assertEquals("123456",person.getPassword());
        assertEquals("tag",person.getTag());
        assertEquals("JE",person.getNationality());
        assertEquals("active",person.getStatus());
        assertEquals("",person.getUsername());
    }

}
*/
