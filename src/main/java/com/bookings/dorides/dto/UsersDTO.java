package com.bookings.dorides.dto;

import com.bookings.dorides.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository @Getter @Setter @NoArgsConstructor
public class UsersDTO {

    private HashMap<String, User> usersList = new HashMap<String, User>();

    public HashMap<String, User> getUsers() {
        return getUsersList();
    }

    public User getUser(String userName) {
        User user = getUsersList().get(userName);
        return user;
    }

    public void addUser(User user) {
        getUsersList().put(user.getUserName(), user);
    }

    public void updateUserDetails(User updatedUser) {
        getUsersList().put(updatedUser.getUserName(), updatedUser);
    }

}
