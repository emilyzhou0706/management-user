package com.example.usermanagement;

import com.example.usermanagement.controller.UserController;
import com.example.usermanagement.entity.*;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.GetPersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

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
    private void setUpNormalProfileReqAdd() {
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        List tag=Arrays.asList("a","b","c");
        profileReqAdd.setTag(tag);
    }
    //test case 1.1
    @Test
    public void testPostBody(){
        setUpNormalProfileReqAdd();

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
    //test case 2.1
    @Test
    public void testGetAllUsers(){
        //is it too bad to init like this?
        Person person1 = getPerson1();
        Person person2 = getPerson2();
        List<Person> list=new ArrayList<>();
        list.add(person1);
        list.add(person2);

        doReturn(list).when(userRepository).findAll();

        List<Person> listResult=(List<Person>) userController.getAllUsers();

        String joinUserName = listResult.stream()
                .map(Person::getFirstName) // This will call person.getName()
                .collect(Collectors.joining(", "));

        String joinGender = listResult.stream()
                .map(Person::getGender) // This will call person.getName()
                .collect(Collectors.joining(", "));

        String joinEmail = listResult.stream()
                .map(Person::getEmail) // This will call person.getName()
                .collect(Collectors.joining(", "));
        assertEquals(2,listResult.size());
        assertEquals("tony, thomas",joinUserName);
        assertEquals("testemail@gmail.com, testemailSSSS@gmail.com",joinEmail);
        assertEquals("male, male",joinGender);
    }


    //test case 3.1
    @Test
    public void testGetOneByIdExcepotionCase(){
        Person person1 = getPerson1();
        String id=person1.getFirstName();

        when(userRepository.findById(id))
                .thenThrow(new RuntimeException());

        Assertions.assertThrows(RuntimeException.class, () -> {
            userController.getOne(id);
        });
    }
    //test case 3.2
    @Test
    public void testGetOneByIdNotFoundCase(){
        Person person1 = getPerson1();
        String id=person1.getFirstName();

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userController.getOne(id);
        });
    }

    //test case 3.3
    @Test
    public void testGetOneByIdNormalCase() throws UserNotFoundException {
        Person person1 = getPerson1();
        String id=person1.getFirstName();
        //using Optional in mock
        when(userRepository.findById(id))
                .thenReturn(Optional.of(person1));
        Person personResult=userController.getOne(id);
        assertEquals("testemail@gmail.com",personResult.getEmail());
    }
    //test case 4.1
    @Test
    public void testDeleteUserByIdExceptionCase() throws UserNotFoundException {
        Person person1 = getPerson1();
        String id=person1.getFirstName();
        when(userRepository.findById(id))
                .thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            userController.deleteUser(id);
        });
    }
    //test case 4.2
    @Test
    public void testDeleteUserNormalCase(){
        //should i do this to change to HashMap?
        Map<String, Boolean> result=new HashMap<>();

        Person person1 = getPerson1();
        String id=person1.getFirstName();
        when(userRepository.findById(id))
                .thenReturn(Optional.of(person1));
        try {
            userController.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        assertEquals(Boolean.TRUE,result.values().stream().findFirst().get());
//        assertEquals(Boolean.TRUE,result.get("deleted"));
    }
    //test case 4.3

    @Test
    public void testDeleteNotFoundCase(){
        Person person1 = getPerson1();
        String id=person1.getFirstName();

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userController.deleteUser(id);
        });
    }
    //test case 5.1
    @Test
    public void testUpdateUserByIdExceptionCase() throws UserNotFoundException {
        setUpNormalProfileReqAdd();
        Person person1 = getPerson1();
        String id=person1.getFirstName();
        when(userRepository.findById(id))
                .thenThrow(new RuntimeException());

            //or assert e type itself
            Assertions.assertThrows(RuntimeException.class, () -> {
                userController.updateUser(id, profileReqAdd);
            });

    }
    //test case 5.2
    @Test
    public void testUpdateUserNormalCase(){
        setUpNormalProfileReqAdd();

        Person person1 = getPerson1();
        String id=person1.getFirstName();
        when(userRepository.findById(id))
                .thenReturn(Optional.of(person1));
        ResponseEntity<Person> entity= null;
        try {
            entity = userController.updateUser(id,profileReqAdd);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(entity);


    }
    //test case 5.3
    @Test
    public void testUpdateNotFoundCase(){
        setUpNormalProfileReqAdd();

        Person person1 = getPerson1();
        String id=person1.getFirstName();

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userController.updateUser(id,profileReqAdd);
        });
    }

    private Person getPerson2() {
        Person person2 = new Person();
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
        return person2;
    }

    private Person getPerson1() {
        Person person1 = new Person();
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
        return person1;
    }
}


