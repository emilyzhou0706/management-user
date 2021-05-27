package com.example.usermanagement;

import com.example.usermanagement.controller.UserController;
import com.example.usermanagement.entity.*;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.GetPersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

//it's to test the userController in java object way
@ExtendWith(MockitoExtension.class)
public class UserControllerTestsByJavaObjectWay {
    private static final Logger log = LoggerFactory.getLogger(UserManagementApplication.class);

    @Mock
    UserRepository userRepository;

    @Mock
    GetPersonServiceImpl getPersonServiceImpl;

    @InjectMocks
    UserController userController;

    private ProfileReqAdd profileReqAdd;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        profileReqAdd=new ProfileReqAdd();
    }

    @Test
    public void testPostBody(){
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        List tag=Arrays.asList("a","b","c");
        profileReqAdd.setTag(tag);

        Person person1= new Person();
        Person person2= new Person();

        person1.setAge(1);
        person1.setContactNumber("9876654f31");
        person1.setEmail("testemail@gmail.com");
        person1.setUsername("testemail@gmail.com");
        person1.setGender("male");
        person1.setFirstName("tony");
        person1.setPassword("123456");
        person1.setLastName("albertAAA");
        person1.setTag("tag");
        person1.setPassword("123456");
        person1.setNationality("JE");
        person1.setStatus("active");
        doReturn(person1).when(getPersonServiceImpl).getPersonByApi(profileReqAdd);

        userController.postBody(profileReqAdd);
        verify(userRepository, times(1)).save(person1);
    }

    @Test
    public void testGetAllUsers(){
        Person person1=new Person();
        person1.setAge(1);
        person1.setContactNumber("9876654f31");
        person1.setEmail("testemail@gmail.com");
        person1.setUsername("testemail@gmail.com");
        person1.setGender("male");
        person1.setFirstName("tony");
        person1.setPassword("123456");
        person1.setLastName("albertAAA");
        person1.setTag("tag");
        person1.setPassword("123456");
        person1.setNationality("JE");
        person1.setStatus("active");

        Person person2=new Person();
        person2.setAge(10);
        person2.setContactNumber("9876654f88");
        person2.setEmail("testemailSSSS@gmail.com");
        person2.setUsername("testemailSSSS@gmail.com");
        person2.setGender("male");
        person2.setFirstName("thomas");
        person2.setPassword("123499");
        person2.setLastName("albertBB");
        person2.setTag("tag");
        person2.setPassword("123456");
        person2.setNationality("JE");
        person2.setStatus("active");
        List<Person> list=new ArrayList<>();
        list.add(person1);
        list.add(person2);

        doReturn(list).when(userRepository).findAll();

        Iterable<Person> listResult=userController.getAllUsers();
        System.out.println(listResult.toString());
    }
}


