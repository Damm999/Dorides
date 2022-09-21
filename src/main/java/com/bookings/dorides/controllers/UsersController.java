package com.bookings.dorides.controllers;

import com.bookings.dorides.model.User;
import com.bookings.dorides.service.UsersService;
import com.bookings.dorides.templates.AddUserTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "users")
public class UsersController {

    UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/getUsers")
    public HashMap<String, User> getUsers(){
        return usersService.getUsers();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody AddUserTemplate addUserTemplate){
        usersService.addUser(addUserTemplate.getUserName(), addUserTemplate.getGender(), addUserTemplate.getAge());
    }
}
