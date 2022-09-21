package com.bookings.dorides.service;

import com.bookings.dorides.dto.UsersDTO;
import com.bookings.dorides.enums.Gender;
import com.bookings.dorides.exceptions.DuplicateUserException;
import com.bookings.dorides.exceptions.InvalidGenderException;
import com.bookings.dorides.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    UsersDTO usersDTO;
    private int userId = 0;

    /**
     * Constructor for Userservice class
     * @param usersDTO
     */
    public UsersService(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

    /**
     * Method to get all the users
     * @return
     */
    public HashMap<String, User> getUsers() {
        return usersDTO.getUsers();
    }

    /**
     * Method to add new user
     * @param userName
     * @param gender
     * @param age
     * @throws DuplicateUserException
     */
    public void addUser(String userName, Gender gender, int age) throws DuplicateUserException {
        logger.debug("Adding User:"+ userName+" to db.");
        userId = usersDTO.getUsers().size();
        validateDuplicateExists(userName);
        User newUser = new User(userId, userName, gender, age);
        usersDTO.addUser(newUser);
        logger.info("User:"+ userName+" successfully added.");
    }

    /**
     * Method to validate if username already exists
     * @param userName
     * @return false if not exists
     */
    public boolean validateDuplicateExists(String userName) {
        try {
            User user = usersDTO.getUser(userName);
            if (user != null) {
                throw new DuplicateUserException(userName);
            }
        } catch (NullPointerException ex){
            return false;
        }
        return false;
    }

    public boolean validateUserExists(String userName) {
        try {
            User user = usersDTO.getUser(userName);
            if (user == null) {
                throw new DuplicateUserException(userName);
            }
        } catch (NullPointerException ex){
            return true;
        }
        return true;
    }

    /**
     * Method to update user details
     * @param userName
     * @param details
     */
    public void updateUserDetails(String userName, HashMap<String, String> details) throws Exception {
        logger.debug("Updating user: "+ userName);
        User user = usersDTO.getUser(userName);
        logger.debug("Fetching user details from db");
        for (String key : details.keySet()) {
            if (key.contains("userName")) {
                validateDuplicateExists(details.get(key));
                usersDTO.getUsers().remove(userName);
                userName = details.get(key);
                user.setUserName(userName);
                logger.info("Successfully updated user: "+user.getUserName() + " details");
            } else if (key.contains("gender")){
                String gender = details.get(key).toLowerCase();
                if(gender.equals("m") || gender.equals("f"))
                    user.setGender(key.toLowerCase().equals("m")?Gender.M : Gender.F);
                else
                    throw new InvalidGenderException();
                logger.info("Successfully updated user gender");
            } else if (key.contains("age")) {
                user.setAge(Integer.parseInt(details.get(key)));
                logger.info("Successfully updated user age");
            } else if (key.equals("location")) {
                throw new Exception("Please use updateUserLocation() method to update location details");
            }
        }
        usersDTO.updateUserDetails(user);
        logger.info("updated user details in db..");
    }

    /**
     * Method to update user location
     * @param userName
     * @param location
     */
    public void updateUserLocation(String userName, int [][] location){
        User user = usersDTO.getUser(userName);
        validateUserExists(userName);
        logger.debug("Updating user: "+ userName + " with location: " + user.getUserLocation() + " to-> "+ location);
        user.setUserLocation(location);
        logger.info("Updated user" + userName + " location");
    }
}
