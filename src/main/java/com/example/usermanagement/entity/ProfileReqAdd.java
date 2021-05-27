package com.example.usermanagement.entity;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@Setter
public class ProfileReqAdd {
    @NotBlank(message = "password is mandatory")
    String password;
    String firstName;
    String lastName;
    String email;
    String contactNumber;
    List<String> tag;
}
