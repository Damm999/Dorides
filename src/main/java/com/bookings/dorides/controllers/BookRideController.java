package com.bookings.dorides.controllers;

import com.bookings.dorides.service.DriverService;
import com.bookings.dorides.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/booking")
public class BookRideController {

    UsersService usersService;
    DriverService driverService;

    @Autowired
    public BookRideController(UsersService usersService, DriverService driverService){
        this.usersService = usersService;
        this.driverService = driverService;
    }
}
