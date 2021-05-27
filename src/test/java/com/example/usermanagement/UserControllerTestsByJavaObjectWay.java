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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
      /*  List tag=new ArrayList();
        tag.add("a");
        tag.add("b");
        tag.add("c");*/
        List tag=Arrays.asList("a","b","c");
        profileReqAdd.setTag(tag);

        Person person1= new Person();
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
        doReturn(person1).when(getPersonServiceImpl).getPerson(profileReqAdd);

        userController.postBody(profileReqAdd);
        verify(userRepository, times(1)).save(person1);


    }

}


