package com.bookings.dorides.model;

import com.bookings.dorides.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter @Setter @NoArgsConstructor
public class Driver {

    private int id;
    private String driverName;
    private Gender gender;
    private int age;
    private String carDetails;
    private int [][] driverLocation;
    private boolean isDriverAvailable;
    private double driverEarnings;

    public Driver(int id, String driverName, Gender gender, int age, String carDetails, int[][] driverLocation, boolean isDriverAvailable, double driverEarnings) {
        this.id = id;
        this.driverName = driverName;
        this.gender = gender;
        this.age = age;
        this.carDetails = carDetails;
        this.driverLocation = driverLocation;
        this.isDriverAvailable = isDriverAvailable;
        this.driverEarnings = driverEarnings;
    }

    public Driver(String driverName, Gender gender, int age, String carDetails, int[][] driverLocation, boolean isDriverAvailable, double driverEarnings) {
        this.driverName = driverName;
        this.gender = gender;
        this.age = age;
        this.carDetails = carDetails;
        this.driverLocation = driverLocation;
        this.isDriverAvailable = isDriverAvailable;
        this.driverEarnings = driverEarnings;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverName='" + driverName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", carDetails='" + carDetails + '\'' +
                ", driverLocation=" + Arrays.toString(driverLocation) +
                ", isDriverAvailable=" + isDriverAvailable +
                ", driverEarnings=" + driverEarnings +
                '}';
    }
}
