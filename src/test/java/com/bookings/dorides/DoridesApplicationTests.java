package com.bookings.dorides;

import com.bookings.dorides.enums.Gender;
import com.bookings.dorides.model.Driver;
import com.bookings.dorides.model.User;
import com.bookings.dorides.service.BookingRideService;
import com.bookings.dorides.service.DriverService;
import com.bookings.dorides.service.UsersService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class DoridesApplicationTests {
    @Autowired
    UsersService usersService;

    @Autowired
    DriverService driverService;

    @Autowired
    BookingRideService bookingRideService;

    @Test
    void verifyAddUsersOperation() throws Exception {
        usersService.addUser("kaushik123", Gender.M, 26);
        usersService.addUser("rama12", Gender.M, 18);
        usersService.addUser("opera123", Gender.M, 44);

        HashMap<String, String> usrDetails = new HashMap<>();
        usrDetails.put("userName", "rama233");

        usersService.updateUserDetails("rama12", usrDetails);

        // Added Assertions
        Assertions.assertThat(usersService.getUsers().get("rama233").getUserName()).isEqualTo("rama233");
        Assertions.assertThatNullPointerException().isThrownBy(() -> {
            usersService.getUsers().get("rama12").getUserName();
        });

        usersService.updateUserLocation("opera123", new int[][]{{10, 10}});
        usersService.updateUserLocation("kaushik123", new int[][]{{11, 11}});
        usersService.updateUserLocation("opera123", new int[][]{{43, 30}});

        HashMap<String, User> usersList = usersService.getUsers();
        for (String user : usersList.keySet()) {
            System.out.println(usersList.get(user));
        }
    }

    @Test
    void verifyAddDriverOperation() {
        driverService.addDriver("Driver 11", Gender.M, 32, "Swift, KA-01-12345", new int[][]{{1, 2}});
        driverService.addDriver("Driver 12", Gender.M, 32, "Swift, KA-01-12345", new int[][]{{1, 2}});
        driverService.addDriver("Driver 13", Gender.M, 32, "Swift, KA-01-12345", new int[][]{{1, 2}});

        Assertions.assertThat(driverService.getAllDrivers().get("Driver 11").getDriverName()).isEqualTo("Driver 11");

        HashMap<String, Driver> driversList = driverService.getAllDrivers();
        for (String user : driversList.keySet()) {
            System.out.println(driversList.get(user));
        }
    }

    @Test
    void findRide() {
        String user1 = "uRider1";
        String user2 = "uRider2";
        String user3 = "uRider3";
        // Adding Users
        usersService.addUser(user1, Gender.M, 18);
        usersService.addUser(user2, Gender.M, 26);
        usersService.addUser(user3, Gender.M, 44);

        usersService.updateUserLocation(user1, new int[][]{{0, 0}});
        usersService.updateUserLocation(user2, new int[][]{{10, 0}});
        usersService.updateUserLocation(user3, new int[][]{{5, 3}});

        Assertions.assertThat(usersService.getUsers().get(user1).getUserName()).isEqualTo(user1);
        // Adding Drivers
        String driver1 = "Driver 1";
        String driver2 = "Driver 2";
        String driver3 = "Driver 3";
        driverService.addDriver(driver1, Gender.M, 32, "Swift, KA-01-12345", new int[][]{{10, 2}});
        driverService.addDriver(driver2, Gender.M, 32, "Swift, KA-01-12345", new int[][]{{10, 2}});
        driverService.addDriver(driver3, Gender.M, 32, "Swift, KA-01-12345", new int[][]{{10, 2}});

        driverService.updateDriverLocation(driver1, new int[][]{{10, 1}});
        driverService.updateDriverLocation(driver2, new int[][]{{11, 10}});
        driverService.updateDriverLocation(driver3, new int[][]{{5, 3}});

        Assertions.assertThat(driverService.getAllDrivers().get(driver3).getDriverName()).isEqualTo(driver3);

        // Booking a ride
        bookingRideService.findRide(user2, new int[][]{{10, 0}}, new int[][]{{15, 3}});
        bookingRideService.chooseRide(user2, driver1);
        Assertions.assertThat(usersService.getUsers().get(user2).getUserLocation()).isEqualTo(new int[][]{{15, 3}});
        bookingRideService.findTotalEarnings();


    }


}
