package com.bookings.dorides.exceptions;

public class DuplicateUserException extends RuntimeException {
    public  DuplicateUserException(String userName){
        super("User: "+ userName +" already Exists in database, please input different user name");
    }
}
