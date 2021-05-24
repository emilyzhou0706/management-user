package com.example.usermanagement.service;

import com.example.usermanagement.entity.Person;
import com.example.usermanagement.entity.ProfileReqAdd;

public interface GetPersonService {
    Person getPerson(ProfileReqAdd profileReqAdd);
}
