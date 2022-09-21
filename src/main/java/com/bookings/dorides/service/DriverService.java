package com.bookings.dorides.service;

import com.bookings.dorides.dto.DriversDTO;
import com.bookings.dorides.enums.Gender;
import com.bookings.dorides.exceptions.DuplicateDriverException;
import com.bookings.dorides.model.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DriverService {
    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
    @Autowired
    DriversDTO driversDTO;

    public DriverService(DriversDTO driversDTO){
        this.driversDTO = driversDTO;
    }

    /**
     * Method to add driver
     * @param driverName
     * @param gender
     * @param age
     * @param carDetails
     * @param driverLocation
     */
    public void addDriver(String driverName, Gender gender, int age, String carDetails, int [][] driverLocation){
        logger.debug("Adding "+ driverName +" as new driver to db.");
        validateIfDriverExists(driverName);
        boolean isDriverAvailable= true;
        int driverSalary = 0;
        Driver driver = new Driver(driverName, gender, age,carDetails, driverLocation, isDriverAvailable, driverSalary);
        driversDTO.addDriver(driver);
        logger.info("New Driver: "+ driverName +"  is added to db.");
    }

    /**
     * Method to get All the drivers in db
     * @return
     */
    public HashMap<String, Driver> getAllDrivers(){
        return driversDTO.getDriversMap();
    }

    public void updateDriverDetails(String driverName, HashMap<String, String> driverDetails){
        System.out.println("Coming soon");
    }

    /**
     * Method to validate if driver already exists
     * @param driverName
     * @return false if he doesn't exists
     */
    public boolean validateIfDriverExists(String driverName){
        try {
            Driver driver = driversDTO.getDriverDetails(driverName);
            if (driver != null) {
                throw new DuplicateDriverException(driverName);
            }
        } catch (NullPointerException nullPointerException){
            return false;
        }
        return false;
    }

    /**
     * Method to update driver location
     * @param driverName
     * @param location
     */
    public void updateDriverLocation(String driverName, int [][] location){
        Driver driver = driversDTO.getDriverDetails(driverName);
        logger.debug("Updating driver location.. ");
        driver.setDriverLocation(location);
        driversDTO.updateDriver(driver);
        logger.info("Updated driver: "+ driverName +" location to: "+ location);
    }

    /**
     * Method to update driver status
     * @param driverName
     * @param isDriving
     */
    public void updateDriverStatus(String driverName, boolean isDriving){
        Driver driver = driversDTO.getDriverDetails(driverName);
        logger.debug("fetching current driver status: "+ driver.isDriverAvailable());
        driver.setDriverAvailable(isDriving);
        driversDTO.updateDriver(driver);
        logger.info("Driver status is updated from: "+ driver.isDriverAvailable() + " -to- "+ isDriving);
    }
}
