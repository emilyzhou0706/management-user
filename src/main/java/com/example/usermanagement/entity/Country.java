package com.example.usermanagement.entity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Country {
    String country_id;
    Double probability;

    public Country(String country_id, Double probability) {
        this.country_id = country_id;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryID='" + country_id + '\'' +
                ", probability=" + probability +
                '}';
    }
}
