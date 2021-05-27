package com.example.usermanagement.entity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GuessGender {
    String name;

    String gender;

    Long probability;

    int count;

    public String toString() {
        return "GuessGender{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", possibility='" + probability + '\'' +
                ", count=" + count +
                '}';
    }
}
