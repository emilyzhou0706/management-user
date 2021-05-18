package com.example.usermanagement.entity;

import lombok.Setter;
import lombok.Getter;

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
    String tag;

}
