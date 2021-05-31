package com.example.usermanagement;

import com.example.usermanagement.controller.UserController;
import com.example.usermanagement.entity.Person;
import com.example.usermanagement.entity.ProfileReqAdd;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.GetPersonServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTestsByMockBean {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetPersonServiceImpl getPersonServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    RestTemplate restTemplate;

    private ProfileReqAdd profileReqAdd;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        profileReqAdd=new ProfileReqAdd();
    }
    //test case 1.1
    @Test
    public void testPostBody(){
        /*profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        List tag=new ArrayList();
        tag.add("a");
        tag.add("b");
        tag.add("c");
        profileReqAdd.setTag(tag);*/
    }

    //test case 1.1
    @Test
    public void addUserTestNormalCase() throws Exception {
        profileReqAdd.setPassword("123456");
        profileReqAdd.setFirstName("tony");
        profileReqAdd.setLastName("albert");
        profileReqAdd.setEmail("testemail@gmail.com");
        profileReqAdd.setContactNumber("9876654f31");
        List tag=new ArrayList();
        tag.add("a");
        tag.add("b");
        tag.add("c");
        profileReqAdd.setTag(tag);
        Person person1 = getPerson1();

        Mockito.when(
                getPersonServiceImpl.getPersonByApi(Mockito.any(ProfileReqAdd.class))).thenReturn(person1);

        String exampleProfileAddReqJson = "{\"password\":\"password\",\"firstName\":\"tony\",\"lastName\":\"ablert\",\"email\":\"emailAddr\",\"contactNumber\":\"contactNumber\",\"tag\":[\"a\",\"b\",\"c\"]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/user-management/add")
                .accept(MediaType.APPLICATION_JSON).content(exampleProfileAddReqJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());
        assertEquals(201,result.getResponse().getStatus());

        /*String expected = "{id:Course1,name:Spring,description:10Steps}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);*/

    }

    private Person getPerson1() {
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
        return person1;
    }

    private Person getPerson2() {
        Person person1= new Person();
        person1.setAge(1);
        person1.setContactNumber("9876654f3AA");
        person1.setEmail("testemail@gmail.com");
        person1.setUsername("testemail@gmail.com");
        person1.setGender("male");
        person1.setFirstName("tonyAA");
        person1.setPassword("123456AA");
        person1.setLastName("albertAA");
        person1.setTag("tag");
        person1.setPassword("123456");
        person1.setNationality("JE");
        person1.setStatus("active");
        return person1;
    }

    //test case 2.1
    @Test
    public void getAlltNormalCase() throws Exception {

        Person person1 = getPerson1();
        Person person2 = getPerson2();

        Mockito.when(
                userRepository.findAll()).thenReturn(Arrays.asList(person1,person2));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user-management/all");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200,result.getResponse().getStatus());
        String resultArrayOfObj=result.getResponse().getContentAsString();
        Gson gson = new Gson();
        Person[] persons = gson.fromJson(resultArrayOfObj, Person[].class);
        assertEquals(2,persons.length);
        System.out.println(persons[0].toString());
        System.out.println(persons[1].toString());
        assertEquals("tony",persons[0].getFirstName());
        assertEquals("tonyAA",persons[1].getFirstName());

        assertEquals("9876654f31",persons[0].getContactNumber());
        assertEquals("9876654f3AA",persons[1].getContactNumber());

    }

    //test case 3.1
    @Test
    public void getByOneNormalCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(person1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user-management/users/{id}",id);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    //test case 3.2

    @Test
    public void getByOneUserNotFoundCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user-management/users/{id}",id);
        //下面这句如果写了，会exception先于assert, 就assert不了了
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    //test case 3.3
    @Test
    public void getByOneThrowExceptionCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user-management/users/{id}",id);
        Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    //test case 4.1
    @Test
    public void deleteByOneNormalCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(person1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/user-management/user/{id}",id);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
        verify(userRepository, times(1)).delete(person1);
    }

    //test case 4.2
    @Test
    public void deleteByOneUserNotFoundCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/user-management/user/{id}",id);

        Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    //test case 4.3
    @Test
    public void deleteByOneThrowExceptionCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/user-management/user/{id}",id);
        Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    //test case 5.1
    @Test
    public void updateByOneNormalCase() throws Exception {
        String exampleProfileAddReqJson = "{\"password\":\"passwordChg\",\"firstName\":\"tonyChg\",\"lastName\":\"ablertChg\",\"email\":\"emailAddr\",\"contactNumber\":\"contactNumberChg\",\"tag\":[\"a\",\"b\",\"c\",\"chg\"]}";
        Gson gson = new Gson();
        ProfileReqAdd profileReqAdd = gson.fromJson(exampleProfileAddReqJson, ProfileReqAdd.class);

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(person1));
        person1.setPassword(profileReqAdd.getPassword());
        person1.setFirstName(profileReqAdd.getFirstName());
        person1.setLastName(profileReqAdd.getLastName());
        person1.setEmail(profileReqAdd.getEmail());
        person1.setContactNumber(profileReqAdd.getContactNumber());
        person1.setTag(profileReqAdd.getTag().stream()
                .collect(Collectors.joining(":")));

        Mockito.when(
                userRepository.save(person1)).thenReturn(person1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/user-management/users/{id}",id)
                .accept(MediaType.APPLICATION_JSON).content(exampleProfileAddReqJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        verify(userRepository, times(1)).save(person1);
        assertEquals(200,result.getResponse().getStatus());
        Person updatedPerson = gson.fromJson(result.getResponse().getContentAsString(), Person.class);

        assertEquals("passwordChg",updatedPerson.getPassword());
        assertEquals("tonyChg",updatedPerson.getFirstName());
        assertEquals("ablertChg",updatedPerson.getLastName());

    }


    //test case 5.2
    @Test
    public void updateByOneUserNotFoundCase() throws Exception {
        String exampleProfileAddReqJson = "{\"password\":\"password\",\"firstName\":\"tony\",\"lastName\":\"ablert\",\"email\":\"emailAddr\",\"contactNumber\":\"contactNumber\",\"tag\":[\"a\",\"b\",\"c\"]}";

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/user-management/users/{id}",id)
                .accept(MediaType.APPLICATION_JSON).content(exampleProfileAddReqJson)
                .contentType(MediaType.APPLICATION_JSON);

        Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }

    //test case 5.3
    @Test
    public void updateByOneThrowExceptionCase() throws Exception {
        String exampleProfileAddReqJson = "{\"password\":\"password\",\"firstName\":\"tony\",\"lastName\":\"ablert\",\"email\":\"emailAddr\",\"contactNumber\":\"contactNumber\",\"tag\":[\"a\",\"b\",\"c\"]}";

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/user-management/users/{id}",id)
                .accept(MediaType.APPLICATION_JSON).content(exampleProfileAddReqJson)
                .contentType(MediaType.APPLICATION_JSON);
        Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });

    }
}
