package com.bookings.dorides.exceptions;

public class DuplicateDriverException extends RuntimeException {
    public DuplicateDriverException(String driverName){
        super("Driver with username: "+driverName+" already Exists in database, please input different user name");
    }
}
