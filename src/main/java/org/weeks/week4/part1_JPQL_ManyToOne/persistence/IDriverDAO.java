package org.weeks.week4.part1_JPQL_ManyToOne.persistence;

import org.weeks.week4.part1_JPQL_ManyToOne.model.Driver;

import java.math.BigDecimal;
import java.util.List;

public interface IDriverDAO {

    // Driver
    void saveDriver(Driver driver);
    Driver getDriverById(String id);
    Driver updateDriver(Driver driver);
    void deleteDriver(String id);
    List<Driver> findAllDriversEmployedAtTheSameYear(String year);
    List<Driver> fetchAllDriversWithSalaryGreaterThan10000();
    double fetchHighestSalary();


    List<String> fetchFirstNameOfAllDrivers();

    long calculateNumberOfDrivers();


    Driver fetchDriverWithHighestSalary();



}
