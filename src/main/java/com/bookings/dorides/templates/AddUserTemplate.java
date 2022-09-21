package com.bookings.dorides.templates;

import com.bookings.dorides.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AddUserTemplate {
    private String userName;
    private int age;
    private Gender gender;

    public AddUserTemplate(String userName, int age, Gender gender) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }
}
