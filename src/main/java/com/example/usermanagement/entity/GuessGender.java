package com.example.usermanagement.entity;

import lombok.Getter;
import lombok.Setter;

public class GuessGender {
    @Getter @Setter
    String name;

    @Getter @Setter
    String gender;

    @Getter @Setter
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
