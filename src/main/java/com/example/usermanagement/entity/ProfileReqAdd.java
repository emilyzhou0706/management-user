package com.example.usermanagement.entity;

import lombok.Setter;
import lombok.Getter;

import java.util.List;
@Getter
@Setter
public class ProfileReqAdd {
    String password;
    String firstName;
    String lastName;
    String email;
    String contactNumber;
    List<String> tag;
}
