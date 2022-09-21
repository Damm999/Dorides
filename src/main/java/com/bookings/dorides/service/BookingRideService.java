package com.bookings.dorides.service;

import com.bookings.dorides.dto.DriversDTO;
import com.bookings.dorides.dto.UsersDTO;
import com.bookings.dorides.model.Driver;
import com.bookings.dorides.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookingRideService {
    private static final Logger logger = LoggerFactory.getLogger(BookingRideService.class);
    int[][] source;
    int[][] destination;
    @Autowired
    UsersDTO usersDTO;

    @Autowired
    DriversDTO driversDTO;

    public BookingRideService(UsersDTO usersDTO, DriversDTO driversDTO) {
        this.usersDTO = usersDTO;
        this.driversDTO = driversDTO;
    }

    public ArrayList<Driver> findRide(String userName, int[][] source, int[][] destination) {
        logger.info("finding ride for user: " + userName);
        this.source = source;
        this.destination = destination;
        ArrayList<Driver> driversList = new ArrayList<>();
        boolean isDriverCheck = false;
        logger.info("Please wait while we search all the available drivers");
        for (String driverName : driversDTO.getDriversMap().keySet()) {
            if (driversDTO.getDriversMap().get(driverName).isDriverAvailable()) {
                isDriverCheck = true;
                driversList.add(driversDTO.getDriversMap().get(driverName));
            }
        }
        if (!isDriverCheck) {
            logger.info("Sorry currently no ride is available");
        } else {
            logger.info("Found " + driversList.size() + " rides, please choose your driver");
        }
        return driversList;
    }

    public void chooseRide(String userName, String driverName) {
        User user = usersDTO.getUser(userName);
        Driver driver = driversDTO.getDriverDetails(driverName);
        boolean isDriverAvailable = false;

        double driverDistance = calculateDistance(user.getUserLocation(), driver.getDriverLocation());
        logger.debug("Driver Name: " + driver.getDriverName() + " User: " + user.getUserName());
        logger.info("Current driver distance: " + driverDistance);
        if (driverDistance <= 5) {
            logger.info("Aha-- You driver is reaching your location....");
            driver.setDriverAvailable(false);
            logger.info("Your trip is started ... Enjoy your ride...");
            double bill = calculateBill(userName, driverName);
            double cost = calculateDriverPayment(driver.getDriverEarnings(), bill);
            driver.setDriverEarnings(cost);
        } else {
            logger.info("Please find the list of Available drivers near to you....");
            for (String driversName : driversDTO.getDriversMap().keySet()) {
                if (calculateDistance(user.getUserLocation(),
                        driversDTO.getDriversMap().get(driversName).getDriverLocation()) <= 5) {
                    System.out.println(driversName);
                    isDriverAvailable = true;
                }
            }
            if (isDriverAvailable)
                logger.info("Choose any of the driver from above names populated");
            else
                logger.info("Sorry please check again later, no driver is available near to your location");
        }
    }

    /**
     * Method to calculate trip cost
     *
     * @param driverSalary
     * @param bill
     * @return
     */
    private double calculateDriverPayment(double driverSalary, double bill) {
        return Math.ceil( driverSalary + bill);
    }


    public double calculateDistance(int[][] source, int[][] destination) {
        double x = Math.pow((destination[0][0] - source[0][0]), 2);
        logger.debug("X: " + x);
        double y = Math.pow((destination[0][1] - source[0][1]), 2);
        logger.debug("Y: " + y);
        return Math.sqrt(x + y);
    }

    /**
     * Method to return total bill for the ride
     *
     * @return
     */
    public double calculateBill(String userName, String driverName) {
        // Update User location
        User user = usersDTO.getUser(userName);
        user.setUserLocation(destination);
        usersDTO.updateUserDetails(user);
        // Update driver location
        Driver driver = driversDTO.getDriverDetails(driverName);
        driver.setDriverLocation(destination);
        driversDTO.updateDriver(driver);

        // Calculating trip cost
        double tripCost = calculateDistance(source, destination);
        logger.info("Your destination has arrived, thanks for choosing our service! Please do come again");
        logger.info("Trip ended with cost: "+ tripCost);
        return tripCost;
    }

    /**
     * Method to fetch driver's total earnings
     */
    public void findTotalEarnings() {
        for(String driverName : driversDTO.getDriversMap().keySet()) {
            double earnings = driversDTO.getDriverDetails(driverName).getDriverEarnings();
            logger.info(driverName+ " earned: $"+ earnings );
        }
    }
}
