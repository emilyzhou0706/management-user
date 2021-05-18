package com.example.usermanagement.entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GuessNation {

    @Getter @Setter
    String name;
    @Getter @Setter
    List<Country> country;

    @Override
    public String toString() {
        return "GuessNation{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
