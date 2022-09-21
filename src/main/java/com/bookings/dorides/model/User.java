package com.bookings.dorides.model;

import com.bookings.dorides.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter @Setter @NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private Gender gender;
    private int age;
    private int [][] userLocation;

    public User(int id, String userName, Gender gender, int age, int [][] userLocation) {
        this.id = id;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.userLocation = userLocation;
    }

    public User(String userName, Gender gender, int age, int [][] userLocation) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.userLocation = userLocation;
    }

    public User(int id, String userName, Gender gender, int age) {
        this.id = id;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", userLocation=" + Arrays.toString(userLocation) +
                '}';
    }
}
