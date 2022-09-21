package com.bookings.dorides.dto;

import com.bookings.dorides.model.Driver;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository @Getter @Setter
public class DriversDTO {

    private HashMap<String, Driver> driversMap = new HashMap<>();
    private int driversCount;

    /**
     * Method to create Drivers list
     * @param driver
     */
    public void addDriver(Driver driver) {
        setDriversCount(getDriversMap().size());
        driver.setId(getDriversCount());
        getDriversMap().put(driver.getDriverName(), driver);
    }

    public Driver getDriverDetails(String driverName){
        return getDriversMap().get(driverName);
    }

    public void updateDriver(Driver driver){
        getDriversMap().put(driver.getDriverName(), driver);
    }
}
