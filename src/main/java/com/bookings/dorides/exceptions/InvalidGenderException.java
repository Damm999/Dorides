package com.bookings.dorides.exceptions;

public class InvalidGenderException extends RuntimeException{
    public InvalidGenderException(){
        super("Please enter a valid gender option either F || M");
    }
}
