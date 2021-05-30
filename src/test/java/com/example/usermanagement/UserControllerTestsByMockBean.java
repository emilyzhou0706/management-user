package com.example.usermanagement;

import com.example.usermanagement.controller.UserController;
import com.example.usermanagement.entity.Person;
import com.example.usermanagement.entity.ProfileReqAdd;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.GetPersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testPostBody(){
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
    }

    @Test
    public void addUserTestNormalCase() throws Exception {
        Person person1 = getPerson1();

        Mockito.when(
                getPersonServiceImpl.getPersonByApi(Mockito.any(ProfileReqAdd.class))).thenReturn(person1);

        String exampleCourseJson = "{\"password\":\"password\",\"firstName\":\"tony\",\"lastName\":\"ablert\",\"email\":\"emailAddr\",\"contactNumber\":\"contactNumber\",\"tag\":[\"a\",\"b\",\"c\"]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/user-management/add")
                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
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

    @Test
    public void getAllTestNormalCase() throws Exception {

//        String exampleCourseJson = "{\"password\":\"password\",\"firstName\":\"tony\",\"lastName\":\"ablert\",\"email\":\"emailAddr\",\"contactNumber\":\"contactNumber\",\"tag\":[\"a\",\"b\",\"c\"]}";


/*        Mockito.when(
                getPersonServiceImpl.getPersonByApi(Mockito.any(ProfileReqAdd.class))).thenReturn(person1);*/

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user-management/all");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void getByOneNormalCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(person1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user-management/users/{id}",id);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    public void getByOneUserNotFoundCase() throws Exception {

        Person person1 = getPerson1();
        String id="testemail@gmail.com";
        Mockito.when(
                userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user-management/users/{id}",id);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

//        System.out.println(result.getResponse().getStatus());
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            mockMvc.perform(requestBuilder).andReturn();
        });
    }


}
