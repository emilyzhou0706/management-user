package com.example.usermanagement.entity;

import lombok.Getter;
import lombok.Setter;

public class Country {
    @Getter @Setter
    String country_id;
    @Getter @Setter
    Double probability;

    @Override
    public String toString() {
        return "Country{" +
                "countryID='" + country_id + '\'' +
                ", probability=" + probability +
                '}';
    }
}
