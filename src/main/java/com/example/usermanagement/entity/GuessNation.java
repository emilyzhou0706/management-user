package com.example.usermanagement.entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GuessNation {

    String name;
    List<Country> country;

    @Override
    public String toString() {
        return "GuessNation{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
