package com.example.usermanagement.entity;

import lombok.Setter;
import lombok.Getter;

import java.util.List;

public class ProfileReqAdd {
    @Getter @Setter
    String password;
    @Getter @Setter
    String firstName;
    @Getter @Setter
    String lastName;
    @Getter @Setter
    String email;
    @Getter @Setter
    String contactNumber;
    @Getter @Setter
    List<String> tag;
}
