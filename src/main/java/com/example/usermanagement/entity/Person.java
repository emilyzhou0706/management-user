package com.example.usermanagement.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "app_user")
@EqualsAndHashCode
public class Person {
    @Id
    @Getter @Setter
    String username;
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
    int age;
    @Getter @Setter
    String gender;
    @Getter @Setter
    String nationality;

    @Getter @Setter
    String tag;
    @Getter @Setter
    String status;

    @Getter @Setter
    @EqualsAndHashCode.Exclude
    String created;
    @EqualsAndHashCode.Exclude
    @Getter @Setter
    String updated;

    public Person() {
    }
/*
    public Person(String firstName, int age, String gender, String nationality) {
        this.firstName = firstName;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
    }*/

    public Person(String username, String password, String firstName, String lastName, String email, String contactNumber, int age, String gender, String nationality, String tag, String status, String created, String updated) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
        this.tag = tag;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }
/*
    public Person(String username, String password, String firstName, String lastName, String email, String contactNumber, int age, String gender, String nationality, String tag) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
        this.tag = tag;
    }*/
}
